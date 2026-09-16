import { UserMenu } from "./UserMenu";

export function PageHeader({ breadcrumb, title }: { breadcrumb: string; title: string }) {
    return (
        <header className="header">
            <div>
                <p className="breadcrumb">{breadcrumb}</p>
                <h1>{title}</h1>
            </div>
            <UserMenu />
        </header>
    );
}
