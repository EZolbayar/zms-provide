export { ApiError } from "./client";
export { usingDummyData } from "./config";
export * from "./types";

import { login, logout } from "./auth";
import { getAccounts } from "./accounts";
import { getRecentChanges, uploadRecentChanges, getCustomersForClients, uploadCitizen } from "./citizen";
import { createCustomer, getAddressMapping, getCustomer, getCustomers, updateCustomer } from "./customers";

/** Single facade over every backend call, grouped to match com.example.terguun.conttoller.ApiController. */
export const api = {
    login,
    logout,
    accounts: getAccounts,
    recentChanges: getRecentChanges,
    uploadRecentChanges,
    customersForClients: getCustomersForClients,
    uploadCitizen,
    customers: getCustomers,
    customer: getCustomer,
    createCustomer,
    updateCustomer,
    addressMapping: getAddressMapping,
};
