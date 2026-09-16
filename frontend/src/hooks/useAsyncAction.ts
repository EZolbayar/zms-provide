"use client";

import { useCallback, useState } from "react";

/** Wraps an async action with busy/message state so pages don't repeat the same try/catch boilerplate. */
export function useAsyncAction() {
    const [busy, setBusy] = useState(false);
    const [message, setMessage] = useState("");
    const [isError, setIsError] = useState(false);

    const run = useCallback(async (action: () => Promise<void>) => {
        setBusy(true);
        setIsError(false);
        try {
            await action();
        } catch (error) {
            setMessage(error instanceof Error ? error.message : "Хүсэлтийг гүйцэтгэж чадсангүй.");
            setIsError(true);
        } finally {
            setBusy(false);
        }
    }, []);

    return { busy, message, isError, setMessage, run };
}
