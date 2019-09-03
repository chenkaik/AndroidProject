package common.android.http.okhttp;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * date: 2019/1/30
 * desc:
 */
public class CookieManager implements CookieJar {

    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    @Override
    public void saveFromResponse(HttpUrl url, @NotNull List<Cookie> cookies) {
        cookieStore.put(url.host(), cookies);
    }

    @NotNull
    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url.host());
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }

    public void clearCookie() {
        cookieStore.clear();
    }

}
