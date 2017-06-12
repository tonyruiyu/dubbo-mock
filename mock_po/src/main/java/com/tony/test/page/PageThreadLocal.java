package com.tony.test.page;

import java.util.HashMap;
import java.util.Map;

public class PageThreadLocal {
	private static ThreadLocal<Page<?>> threadLocal = new ThreadLocal<Page<?>>();

	private PageThreadLocal() {

	}

	public static Page<?> getThreadLocalPage() {
		return threadLocal.get();
	}

	public static void setThreadLocalPage(Page<?> page) {
		threadLocal.set(page);
	}

	public static void removeThreadLocalPage() {
		threadLocal.remove();
	}
	
	public static Object handlerPageParameter(Object parameter) {
        if (parameter != null && parameter instanceof HashMap<?, ?>) {
            Map<?, ?> map = (HashMap<?, ?>) parameter;
            Page<?> page;
            try {
                page = (Page<?>) map.get("page");
            } catch (Exception e) {
                return parameter;
            }
            if (page != null) {
                PageThreadLocal.setThreadLocalPage(page);
            }
        }
        return parameter;
    }
}
