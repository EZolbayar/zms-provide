// Central runtime configuration for the API layer.
export const API_BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL ?? "http://localhost:8081";

// Lets the UI run stand-alone (no backend) for demos; set NEXT_PUBLIC_USE_DUMMY_DATA=false to hit the real API.
export const usingDummyData = process.env.NEXT_PUBLIC_USE_DUMMY_DATA !== "false";
