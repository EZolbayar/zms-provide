import { redirect } from "next/navigation";

// Entry redirect: middleware sends unauthenticated visitors to /login before they ever reach here.
export default function RootPage() {
    redirect("/dashboard");
}