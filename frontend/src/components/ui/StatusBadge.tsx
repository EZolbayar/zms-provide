export function StatusBadge({ status }: { status: string }) {
    const label = status === "A" ? "ИДЭВХТЭЙ" : status === "C" ? "ХААГДСАН" : status;
    return <span className={`status${status === "A" ? "" : " status-muted"}`}>{label}</span>;
}
