export function Notice({ message, tone = "info" }: { message: string; tone?: "info" | "error" }) {
    if (!message) return null;
    return <p className={`notice${tone === "error" ? " error" : ""}`}>{message}</p>;
}
