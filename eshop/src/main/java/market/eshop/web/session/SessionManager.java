package market.eshop.web.session;

import market.eshop.domain.Member;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();
    private static final String SESSION_COOKIE_NAME = "mySessionId";
    /** 
     * 세션 생성
     * sessionId 생성 ( 임의의 추정 불가능한 랜덤 값 )
     * 세션 저장소에 sessionId와 보관할 값 저장
     * sessionId로 응답 쿠키를 생성해서 클라이언트에 전달
     * */
    
    public void createSession(Object value, HttpServletResponse response) {
        //세션 Id 생성 후 값을 세션에 저장

        String sessionId = UUID.randomUUID().toString();    //랜덤으로 세션 아이디 생성
        sessionStore.put(sessionId, value);

        //쿠키 생성
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(mySessionCookie);
    }

    /** 세션 조회 */
    public Object getSession(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if(sessionCookie == null)
            return null;

        return sessionStore.get(sessionCookie.getValue());
    }

    /** 쿠키 찾기 */
    public Cookie findCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();    //모든 쿠키 정보를 가져옴
        if(cookies == null)
            return null;

        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()              //Any는 순서상관없이 제일빨리나온 것
                .orElse(null);  //쿠키가 없으면 null반환
    }

    /** 세션 만료 */
    public void expire(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);

        //쿠키 삭제
        if(sessionCookie != null)
            sessionStore.remove(sessionCookie.getValue());

    }
}
