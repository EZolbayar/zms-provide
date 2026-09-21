"use client";

import { useEffect, useMemo, useState } from "react";
import { api, CLIENT_TYPE_CITIZEN, CLIENT_TYPE_LEGAL_ENTITY, type AddressMapping, type Customer, type CustomerRegistration } from "@/lib/api";
import { PageHeader } from "@/components/layout/PageHeader";
import { Notice } from "@/components/ui/Notice";
import { useAuth } from "@/contexts/AuthContext";
import { useAsyncAction } from "@/hooks/useAsyncAction";

const PAGE_SIZE = 10;

const EMPTY_FORM: CustomerRegistration = {
    clientType: CLIENT_TYPE_CITIZEN,
    branchId: "",
    clientName: "",
    directorName: "",
    familyName: "",
    firstName: "",
    civilId: "",
    pinId: "",
    nationalId: "",
    orgPinId: "",
    birthDate: "",
    aimagCityName: "",
    aimagCityCode: "",
    soumDistrictName: "",
    soumDistrictCode: "",
    bagKhorooName: "",
    bagKhorooCode: "",
    streetName: "",
    address1: "",
    address2: "",
    phone1: "",
    phone2: "",
    mobile: "",
    fax: "",
    email: "",
    isStaff: false,
    isShareHolder: false,
    reminder: "",
};

function displayName(customer: Customer) {
    return customer.clientName || [customer.familyName, customer.firstName].filter(Boolean).join(" ") || "—";
}

function clientTypeLabel(clientType?: string) {
    if (clientType === CLIENT_TYPE_LEGAL_ENTITY) return "Хуулийн этгээд";
    if (clientType === CLIENT_TYPE_CITIZEN) return "Монгол улсын иргэн";
    return clientType ?? "—";
}

export default function CustomersPage() {
    const { user } = useAuth();
    const [customers, setCustomers] = useState<Customer[]>([]);
    const [addressMapping, setAddressMapping] = useState<AddressMapping[]>([]);
    const [form, setForm] = useState<CustomerRegistration>(EMPTY_FORM);
    const [formOpen, setFormOpen] = useState(false);
    const [editingCustomerId, setEditingCustomerId] = useState<string | null>(null);
    const [search, setSearch] = useState("");
    const [page, setPage] = useState(1);
    const { busy: listBusy, message: listMessage, isError: listIsError, setMessage: setListMessage, run: runList } = useAsyncAction();
    const { busy, message, isError, setMessage, run } = useAsyncAction();

    const isLegalEntity = form.clientType === CLIENT_TYPE_LEGAL_ENTITY;

    const loadCustomers = () =>
        void runList(async () => {
            const result = await api.customers();
            setCustomers(result);
            setListMessage(`${result.length} харилцагчийн бүртгэл ачааллаа.`);
        });

    useEffect(() => {
        void runList(async () => {
            const [customerList, mapping] = await Promise.all([api.customers(), api.addressMapping()]);
            setCustomers(customerList);
            setAddressMapping(mapping);
        });
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    // ADDRESS_MAPPING лавлахаас аймаг/хот болон сонгосон аймгийн сум/дүүргүүд.
    const cityNames = useMemo(
        () => [...new Set(addressMapping.map((row) => row.cityName ?? "").filter(Boolean))].sort((a, b) => a.localeCompare(b, "mn")),
        [addressMapping],
    );
    // Засах үед хадгалсан ХУР кодоор (аймаг + сум хос, сумын код дангаараа давхцдаг), олдохгүй бол нэрээр тааруулна.
    const selectedDistrict = addressMapping.find(
        (row) =>
            (form.aimagCityCode && form.soumDistrictCode && row.cityCodeXyp === form.aimagCityCode && row.districtCodeXyp === form.soumDistrictCode) ||
            (row.cityName === form.aimagCityName && row.districtName === form.soumDistrictName),
    );
    const selectedCity = selectedDistrict?.cityName ?? (cityNames.includes(form.aimagCityName ?? "") ? (form.aimagCityName ?? "") : "");
    const districtOptions = useMemo(
        () =>
            addressMapping
                .filter((row) => row.cityName === selectedCity && row.districtName)
                .sort((a, b) => (a.districtName ?? "").localeCompare(b.districtName ?? "", "mn")),
        [addressMapping, selectedCity],
    );

    const selectCity = (cityName: string) =>
        setForm((current) => ({
            ...current,
            aimagCityName: cityName,
            aimagCityCode: addressMapping.find((row) => row.cityName === cityName)?.cityCodeXyp ?? "",
            soumDistrictName: "",
            soumDistrictCode: "",
        }));

    // Сонголтын утга нь давхцалгүй үндсэн DISTRICT_CODE, харин хадгалахдаа ХУР (*_XYP) кодыг бичнэ.
    const selectDistrict = (districtCode: string) => {
        const row = addressMapping.find((item) => item.districtCode === districtCode);
        setForm((current) => ({
            ...current,
            aimagCityName: row?.cityName ?? current.aimagCityName,
            aimagCityCode: row?.cityCodeXyp ?? current.aimagCityCode,
            soumDistrictName: row?.districtName ?? "",
            soumDistrictCode: row?.districtCodeXyp ?? "",
        }));
    };

    const setField = <K extends keyof CustomerRegistration>(field: K, value: CustomerRegistration[K]) =>
        setForm((current) => ({ ...current, [field]: value }));

    // Төрөл солиход нөгөө төрлийн талбарууд үлдэж, хамааралгүй утга хадгалагдахаас сэргийлнэ.
    const changeClientType = (clientType: string) =>
        setForm((current) => ({
            ...current,
            clientType,
            ...(clientType === CLIENT_TYPE_LEGAL_ENTITY
                ? { familyName: "", firstName: "", civilId: "", birthDate: "" }
                : { clientName: "", directorName: "", orgPinId: "" }),
        }));

    // "Шинээр бүртгэх" товч дарахад маягтыг цэвэрлэж, засах горимоос гаргана.
    const openNewForm = () => {
        setEditingCustomerId(null);
        setForm(EMPTY_FORM);
        setFormOpen(true);
    };

    // Мөрөн дэх "Засах" товч дарахад тухайн харилцагчийн мэдээллийг маягтад ачаална.
    const openEditForm = (customer: Customer) => {
        setEditingCustomerId(customer.customerId);
        setForm({
            clientType: customer.clientType ?? CLIENT_TYPE_CITIZEN,
            branchId: customer.branchId ?? "",
            clientName: customer.clientName ?? "",
            directorName: customer.directorName ?? "",
            familyName: customer.familyName ?? "",
            firstName: customer.firstName ?? "",
            civilId: customer.civilId ?? "",
            pinId: customer.pinId ?? "",
            nationalId: customer.nationalId ?? "",
            orgPinId: customer.orgPinId ?? "",
            birthDate: customer.birthDate ?? "",
            aimagCityName: customer.aimagCityName ?? "",
            aimagCityCode: customer.aimagCityCode ?? "",
            soumDistrictName: customer.soumDistrictName ?? "",
            soumDistrictCode: customer.soumDistrictCode ?? "",
            bagKhorooName: customer.bagKhorooName ?? "",
            bagKhorooCode: customer.bagKhorooCode ?? "",
            streetName: customer.streetName ?? "",
            address1: customer.address1 ?? "",
            address2: customer.address2 ?? "",
            phone1: customer.phone1 ?? "",
            phone2: customer.phone2 ?? "",
            mobile: customer.mobile ?? "",
            fax: customer.fax ?? "",
            email: customer.email ?? "",
            isStaff: customer.isStaff ?? false,
            isShareHolder: customer.isShareHolder ?? false,
            reminder: customer.reminder ?? "",
        });
        setFormOpen(true);
    };

    const closeForm = () => {
        setFormOpen(false);
        setEditingCustomerId(null);
        setForm(EMPTY_FORM);
    };

    const submit = (event: React.FormEvent) => {
        event.preventDefault();
        void run(async () => {
            // Хоосон талбаруудыг серверт огт илгээхгүй — сервер тэдгээрийг NULL болгож хадгална.
            const payload = Object.fromEntries(
                Object.entries(form).filter(([, value]) => value !== "" && value !== undefined)
            ) as CustomerRegistration;

            if (editingCustomerId != null) {
                const updated = await api.updateCustomer(editingCustomerId, payload, user?.userId);
                setCustomers((current) => current.map((customer) => (customer.customerId === updated.customerId ? updated : customer)));
                setMessage(`${displayName(updated)} харилцагч №${updated.customerId} дугаарын мэдээлэл шинэчлэгдлээ.`);
            } else {
                const created = await api.createCustomer(payload, user?.userId);
                setCustomers((current) => [created, ...current]);
                setPage(1);
                setMessage(`${displayName(created)} харилцагч №${created.customerId} дугаараар амжилттай бүртгэгдлээ.`);
            }
            closeForm();
        });
    };

    const visibleCustomers = customers.filter((customer) =>
        [displayName(customer), customer.civilId, customer.pinId, customer.orgPinId, customer.mobile, customer.email]
            .filter(Boolean)
            .some((value) => String(value).toLowerCase().includes(search.toLowerCase()))
    );
    const totalPages = Math.max(1, Math.ceil(visibleCustomers.length / PAGE_SIZE));
    const currentPage = Math.min(page, totalPages);
    const pagedCustomers = visibleCustomers.slice((currentPage - 1) * PAGE_SIZE, currentPage * PAGE_SIZE);

    return (
        <>
            <PageHeader breadcrumb="АДМИН / ХАРИЛЦАГЧИЙН БҮРТГЭЛ" title="Харилцагч бүртгэх" />

            <section className="inventory-panel">
                <div className="panel-toolbar">
                    <div>
                        <h3>{editingCustomerId != null ? `Харилцагч засах — №${editingCustomerId}` : "Шинэ харилцагч"}</h3>
                        <p>Регистрийн дугаар болон засаг захиргааны нэгжийн мэдээллийг бүрэн бөглөнө үү</p>
                    </div>
                    <button className={formOpen ? "outline-button" : "primary-button"} onClick={() => (formOpen ? closeForm() : openNewForm())}>
                        {formOpen ? "Хаах" : "Шинээр бүртгэх"}
                    </button>
                </div>

                {formOpen && (
                    <form className="customer-form" onSubmit={submit}>
                        <fieldset>
                            <legend>Үндсэн мэдээлэл</legend>
                            <div className="form-grid">
                                <label>
                                    <span>ХАРИЛЦАГЧИЙН ТӨРӨЛ *</span>
                                    <select value={form.clientType} onChange={(event) => changeClientType(event.target.value)}>
                                        <option value={CLIENT_TYPE_CITIZEN}>Монгол улсын иргэн</option>
                                        <option value={CLIENT_TYPE_LEGAL_ENTITY}>Хуулийн этгээд</option>
                                    </select>
                                </label>
                                <label>
                                    <span>САЛБАРЫН ДУГААР</span>
                                    <input value={form.branchId ?? ""} onChange={(event) => setField("branchId", event.target.value)} placeholder="0013384489" />
                                </label>
                                {isLegalEntity ? (
                                    <>
                                        <label>
                                            <span>ХУУЛИЙН ЭТГЭЭДИЙН НЭР *</span>
                                            <input value={form.clientName ?? ""} onChange={(event) => setField("clientName", event.target.value)} />
                                        </label>
                                        <label>
                                            <span>ЗАХИРЛЫН НЭР</span>
                                            <input value={form.directorName ?? ""} onChange={(event) => setField("directorName", event.target.value)} />
                                        </label>
                                        <label>
                                            <span>ХУУЛИЙН ЭТГЭЭДИЙН РЕГИСТР *</span>
                                            <input value={form.orgPinId ?? ""} onChange={(event) => setField("orgPinId", event.target.value)} placeholder="3384489" />
                                        </label>
                                    </>
                                ) : (
                                    <>
                                        <label>
                                            <span>ОВОГ *</span>
                                            <input value={form.firstName ?? ""} onChange={(event) => setField("firstName", event.target.value)} required />
                                        </label>
                                        <label>
                                            <span>НЭР *</span>
                                            <input value={form.clientName ?? ""} onChange={(event) => setField("clientName", event.target.value)} required />
                                        </label>
                                        <label>
                                            <span>УРГИЙН ОВОГ</span>
                                            <input value={form.familyName ?? ""} onChange={(event) => setField("familyName", event.target.value)} />
                                        </label>
                                        <label>
                                            <span>ИРГЭНИЙ БҮРТГЭЛИЙН ДУГААР *</span>
                                            <input
                                                value={form.civilId ?? ""}
                                                onChange={(event) => setField("civilId", event.target.value)}
                                                placeholder="888954521912"
                                                inputMode="numeric"
                                                maxLength={12}
                                            />
                                        </label>
                                        <label>
                                            <span>РЕГИСТРИЙН ДУГААР *</span>
                                            <input
                                                value={form.pinId ?? ""}
                                                onChange={(event) => setField("pinId", event.target.value.toUpperCase())}
                                                placeholder="УБ90051234"
                                            />
                                        </label>
                                        <label>
                                            <span>ТӨРСӨН ОГНОО *</span>
                                            <input type="date" value={form.birthDate ?? ""} onChange={(event) => setField("birthDate", event.target.value)} required />
                                        </label>
                                    </>
                                )}
                                <label>
                                    <span>ИРГЭНИЙ ҮНЭМЛЭХИЙН ДУГААР</span>
                                    <input value={form.nationalId ?? ""} onChange={(event) => setField("nationalId", event.target.value)} />
                                </label>
                            </div>
                        </fieldset>

                        <fieldset>
                            <legend>Хаяг</legend>
                            <div className="form-grid">
                                <label>
                                    <span>АЙМАГ / ХОТ *</span>
                                    <select value={selectedCity} onChange={(event) => selectCity(event.target.value)} required>
                                        <option value="">— Сонгоно уу —</option>
                                        {cityNames.map((name) => (
                                            <option key={name} value={name}>
                                                {name}
                                            </option>
                                        ))}
                                    </select>
                                </label>
                                <label>
                                    <span>СУМ / ДҮҮРЭГ *</span>
                                    <select value={selectedDistrict?.districtCode ?? ""} onChange={(event) => selectDistrict(event.target.value)} disabled={!selectedCity} required>
                                        <option value="">{selectedCity ? "— Сонгоно уу —" : "Эхлээд аймаг / хот сонгоно уу"}</option>
                                        {districtOptions.map((row) => (
                                            <option key={row.districtCode} value={row.districtCode}>
                                                {row.districtName}
                                            </option>
                                        ))}
                                    </select>
                                </label>
                                <label>
                                    <span>БАГ / ХОРОО *</span>
                                    <input value={form.bagKhorooName ?? ""} onChange={(event) => setField("bagKhorooName", event.target.value)} placeholder="1-р хороо" required />
                                </label>
                                <label>
                                    <span>ГУДАМЖ</span>
                                    <input value={form.streetName ?? ""} onChange={(event) => setField("streetName", event.target.value)} placeholder="Их сургуулийн гудамж" />
                                </label>
                                <label className="form-span-2">
                                    <span>ДЭЛГЭРЭНГҮЙ ХАЯГ</span>
                                    <input value={form.address1 ?? ""} onChange={(event) => setField("address1", event.target.value)} placeholder="Хоосон бол дээрх талбаруудаас автоматаар угсарна" />
                                </label>
                                <label className="form-span-2">
                                    <span>НЭМЭЛТ ХАЯГ</span>
                                    <input value={form.address2 ?? ""} onChange={(event) => setField("address2", event.target.value)} />
                                </label>
                            </div>
                        </fieldset>

                        <fieldset>
                            <legend>Холбоо барих</legend>
                            <div className="form-grid">
                                <label>
                                    <span>ГАР УТАС</span>
                                    <input value={form.mobile ?? ""} onChange={(event) => setField("mobile", event.target.value)} placeholder="99112233" />
                                </label>
                                <label>
                                    <span>УТАС 1</span>
                                    <input value={form.phone1 ?? ""} onChange={(event) => setField("phone1", event.target.value)} />
                                </label>
                                <label>
                                    <span>УТАС 2</span>
                                    <input value={form.phone2 ?? ""} onChange={(event) => setField("phone2", event.target.value)} />
                                </label>
                                <label>
                                    <span>ФАКС</span>
                                    <input value={form.fax ?? ""} onChange={(event) => setField("fax", event.target.value)} />
                                </label>
                                <label>
                                    <span>И-МЭЙЛ</span>
                                    <input type="email" value={form.email ?? ""} onChange={(event) => setField("email", event.target.value)} />
                                </label>
                                <label className="form-span-2">
                                    <span>ТЭМДЭГЛЭЛ</span>
                                    <input value={form.reminder ?? ""} onChange={(event) => setField("reminder", event.target.value)} />
                                </label>
                            </div>
                            <div className="form-checks">
                                <label className="check-field">
                                    <input type="checkbox" checked={form.isStaff ?? false} onChange={(event) => setField("isStaff", event.target.checked)} />
                                    <span>Ажилтан</span>
                                </label>
                                <label className="check-field">
                                    <input type="checkbox" checked={form.isShareHolder ?? false} onChange={(event) => setField("isShareHolder", event.target.checked)} />
                                    <span>Хувьцаа эзэмшигч</span>
                                </label>
                            </div>
                        </fieldset>

                        <div className="form-actions">
                            <button
                                type="button"
                                className="outline-button"
                                onClick={() => (editingCustomerId != null ? closeForm() : setForm(EMPTY_FORM))}
                                disabled={busy}
                            >
                                {editingCustomerId != null ? "Цуцлах" : "Цэвэрлэх"}
                            </button>
                            <button type="submit" className="primary-button" disabled={busy}>
                                {busy
                                    ? (editingCustomerId != null ? "Хадгалж байна..." : "Бүртгэж байна...")
                                    : (editingCustomerId != null ? "Хадгалах" : "Бүртгэх")}
                            </button>
                        </div>

                        <Notice message={message} tone={isError ? "error" : "info"} />
                    </form>
                )}
                {!formOpen && <Notice message={message} tone={isError ? "error" : "info"} />}
            </section>

            <section className="inventory-panel">
                <div className="panel-toolbar">
                    <div>
                        <h3>Бүртгэгдсэн харилцагчид</h3>
                        <p>TBCUSTOMERS хүснэгтэд бүртгэлтэй харилцагчид</p>
                    </div>
                    <div className="toolbar-actions">
                        <label className="search-field">
                            <span>ХАЙХ</span>
                            <input
                                value={search}
                                onChange={(event) => {
                                    setSearch(event.target.value);
                                    setPage(1);
                                }}
                                placeholder="Нэр, регистр, утас..."
                            />
                        </label>
                        <button className="outline-button" onClick={loadCustomers} disabled={listBusy}>
                            Шинэчлэх
                        </button>
                    </div>
                </div>
                <div className="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>Дугаар</th>
                                <th>Овог</th>
                                <th>Нэр</th>
                                <th>Төрөл</th>
                                <th>Регистр</th>
                                <th>Иргэний бүртгэлийн дугаар</th>
                                <th>Хаяг</th>
                                <th>Утас</th>
                                <th>Бүртгэсэн</th>
                                <th />
                            </tr>
                        </thead>
                        <tbody>
                            {pagedCustomers.length > 0 ? (
                                pagedCustomers.map((customer) => (
                                    <tr key={customer.customerId}>
                                        <td className="account-id">{customer.customerId}</td>
                                        <td>{customer.firstName || "—"}</td>
                                        <td>{displayName(customer)}</td>
                                        <td>{clientTypeLabel(customer.clientType)}</td>
                                        <td>{customer.pinId || customer.orgPinId || "—"}</td>
                                        <td>{customer.civilId || "—"}</td>
                                        <td>
                                            {[customer.aimagCityName, customer.soumDistrictName, customer.bagKhorooName, customer.streetName]
                                                .filter(Boolean)
                                                .join(" ") || customer.address1 || "—"}
                                        </td>
                                        <td>{customer.mobile || customer.phone1 || "—"}</td>
                                        <td>{customer.createdBy || "—"}</td>
                                        <td>
                                            <button type="button" className="details-button" onClick={() => openEditForm(customer)}>
                                                Засах
                                            </button>
                                        </td>
                                    </tr>
                                ))
                            ) : (
                                <tr><td className="empty-state" colSpan={10}>{listBusy ? "Ачааллаж байна..." : "Бүртгэгдсэн харилцагч алга."}</td></tr>
                            )}
                        </tbody>
                    </table>
                </div>
                {visibleCustomers.length > 0 && (
                    <div className="pagination">
                        <span>
                            Нийт {visibleCustomers.length} бүртгэлээс {(currentPage - 1) * PAGE_SIZE + 1}-{Math.min(currentPage * PAGE_SIZE, visibleCustomers.length)}-г харуулж байна
                        </span>
                        <div className="pagination-controls">
                            <button className="outline-button" onClick={() => setPage((p) => p - 1)} disabled={currentPage <= 1}>
                                Өмнөх
                            </button>
                            <span className="pagination-current">{currentPage} / {totalPages}</span>
                            <button className="outline-button" onClick={() => setPage((p) => p + 1)} disabled={currentPage >= totalPages}>
                                Дараах
                            </button>
                        </div>
                    </div>
                )}
                <Notice message={listMessage} tone={listIsError ? "error" : "info"} />
            </section>
        </>
    );
}
