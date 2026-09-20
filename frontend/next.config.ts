import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  // Статик файл болгон build хийж, backend (Spring Boot) өөрөө хүргэнэ — тусдаа frontend сервер хэрэггүй.
  output: "export",
  images: { unoptimized: true },
};

export default nextConfig;
