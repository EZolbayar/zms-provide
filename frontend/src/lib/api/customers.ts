import { request, simulate } from "./client";
import { usingDummyData } from "./config";
import { CLIENT_TYPE_CITIZEN, type AddressMapping, type Customer, type CustomerRegistration } from "./types";

const dummyCustomers: Customer[] = [
    {
        customerId: "00001",
        branchId: "0013384489",
        clientType: CLIENT_TYPE_CITIZEN,
        clientName: "ГАНБААТАР БАТ-ЭРДЭНЭ",
        familyName: "ГАНБААТАР",
        firstName: "БАТ-ЭРДЭНЭ",
        civilId: "888954521912",
        pinId: "УБ90051234",
        birthDate: "1990-05-12",
        mobile: "99112233",
        email: "bat-erdene@example.mn",
        aimagCityName: "Улаанбаатар хот",
        aimagCityCode: "11",
        soumDistrictName: "Сүхбаатар дүүрэг",
        soumDistrictCode: "1101",
        bagKhorooName: "1-р хороо",
        bagKhorooCode: "110101",
        streetName: "Их сургуулийн гудамж",
        address1: "Улаанбаатар хот Сүхбаатар дүүрэг 1-р хороо Их сургуулийн гудамж",
        createdOn: "2026-09-01T09:14:00",
        createdBy: "admin",
    },
];

/** GET /api/customers - com.example.terguun.conttoller.ApiController#getCustomers */
export function getCustomers(): Promise<Customer[]> {
    return usingDummyData ? simulate(dummyCustomers) : request<Customer[]>("/customers");
}

/** GET /api/customers/{customerId} - com.example.terguun.conttoller.ApiController#getCustomer */
export function getCustomer(customerId: string): Promise<Customer> {
    return usingDummyData ? simulate(dummyCustomers[0]) : request<Customer>(`/customers/${customerId}`);
}

/** POST /api/customers - com.example.terguun.conttoller.ApiController#createCustomer */
export function createCustomer(customer: CustomerRegistration, userId?: string): Promise<Customer> {
    if (usingDummyData) {
        // Демо горимд серверийн дугаарлалтыг дуурайж, сая нэмсэн дугаараас дараагийнхыг олгоно.
        const nextNumeric = Math.max(0, ...dummyCustomers.map((c) => Number(c.customerId) || 0)) + 1;
        return simulate({ ...customer, customerId: String(nextNumeric).padStart(5, "0"), createdBy: userId, createdOn: new Date().toISOString() });
    }
    return request<Customer>("/customers", {
        method: "POST",
        headers: userId ? { "X-User-Id": userId } : undefined,
        body: JSON.stringify(customer),
    });
}

/** PUT /api/customers/{customerId} - com.example.terguun.conttoller.ApiController#updateCustomer */
export function updateCustomer(customerId: string, customer: CustomerRegistration, userId?: string): Promise<Customer> {
    if (usingDummyData) {
        return simulate({ ...customer, customerId, modifiedBy: userId, modifiedOn: new Date().toISOString() });
    }
    return request<Customer>(`/customers/${customerId}`, {
        method: "PUT",
        headers: userId ? { "X-User-Id": userId } : undefined,
        body: JSON.stringify(customer),
    });
}

/** GET /api/address-mapping - ApiController#getAddressMapping */
export function getAddressMapping(): Promise<AddressMapping[]> {
    if (usingDummyData) {
        return simulate([
            { cityCode: "11000", cityCodeXyp: "11", cityName: "Улаанбаатар", districtCode: "14000", districtCodeXyp: "19", districtName: "Сүхбаатар дүүрэг" },
            { cityCode: "11000", cityCodeXyp: "11", cityName: "Улаанбаатар", districtCode: "13000", districtCodeXyp: "10", districtName: "Баянзүрх дүүрэг" },
            { cityCode: "21000", cityCodeXyp: "21", cityName: "Дорнод аймаг", districtCode: "21001", districtCodeXyp: "01", districtName: "Хэрлэн сум" },
        ]);
    }
    return request<AddressMapping[]>("/address-mapping");
}
