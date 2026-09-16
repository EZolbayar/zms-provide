export { ApiError } from "./client";
export { usingDummyData } from "./config";
export * from "./types";

import { login, logout } from "./auth";
import { getAccounts } from "./accounts";
import { getLatestCustomer, getCustomersForClients, uploadCitizen } from "./citizen";

/** Single facade over every backend call, grouped to match com.example.terguun.conttoller.ApiController. */
export const api = {
    login,
    logout,
    accounts: getAccounts,
    latestCustomer: getLatestCustomer,
    customersForClients: getCustomersForClients,
    uploadCitizen,
};
