import { NextRequest, NextResponse } from "next/server";

const SESSION_COOKIE = "zms_session";
const PUBLIC_PATHS = ["/login"];

export function middleware(request: NextRequest) {
    const { pathname } = request.nextUrl;
    const hasSession = request.cookies.has(SESSION_COOKIE);
    const isPublicPath = PUBLIC_PATHS.some((path) => pathname.startsWith(path));

    if (!hasSession && !isPublicPath) {
        const loginUrl = new URL("/login", request.url);
        loginUrl.searchParams.set("from", pathname);
        return NextResponse.redirect(loginUrl);
    }

    if (hasSession && isPublicPath) {
        return NextResponse.redirect(new URL("/dashboard", request.url));
    }

    return NextResponse.next();
}

export const config = {
    matcher: ["/((?!api|_next/static|_next/image|favicon.ico).*)"],
};
