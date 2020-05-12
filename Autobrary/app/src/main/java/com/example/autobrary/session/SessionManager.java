package com.example.autobrary.session;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class SessionManager {
    private static String sessionId = "";
    private static int sessionTime = 30 * 60000; //ms 단위 -> (분 * 60000) = ms단위 변환
    private static long makeDate;
    private static HashMap<String, String> attribute = new HashMap<String, String>();
    public static boolean validSession(String sessionId){ //존재하는 세션확인
        boolean result = false;
        if(validSessionTime()) { //세션시간이 유효하면
            if (sessionId.equals(SessionManager.sessionId)) {
                result = true;
            }
        }
        return result;
    }
    private static void makeSession(){ //세션생성후 id 가져오기
        makeDate = System.currentTimeMillis ();
        sessionId = UUID.randomUUID().toString();
    }

    public static void setSessionTime(int sessionTime){ //세션시간 설정
        SessionManager.sessionTime = sessionTime;
    }

    public static String getSessionId(){ //세션 아이디 출력
        return sessionId.toString();
    }

    public static int getSessionTime(){ //세션시간 출력
        return SessionManager.sessionTime;
    }

    public static void invalidSession(){ //세션 무효화
        SessionManager.sessionId = null;
    }

    public static void refreshSession(){ //세션시간 갱신
        makeDate = System.currentTimeMillis ();
    }

    public static void setAttribute(String key, String data){
        if(sessionId.getBytes().length <= 0){ //세션이 없을경우
            makeSession();
        }
        if(validSessionTime()) { //세션시간이 유효하면
            refreshSession(); //세션시간 갱신
            attribute.put(key, data);
        }else{ //세션시간이 지났다면
            attribute.clear(); //속성값 초기화
            makeSession(); //새로운 세션 생성
            attribute.put(key, data);
        }
    }

    public static void removeAttribute(String key){
        if(sessionId.getBytes().length <= 0){ //세션이 없을경우
            makeSession();
        }
        if(validSessionTime()) { //세션시간이 유효하면
            refreshSession(); //세션시간 갱신
            attribute.remove(key);
        }else{ //세션시간이 지났다면
            attribute.clear(); //속성값 초기화
        }
    }

    public static String getAttribute(String key){
        if(sessionId.getBytes().length <= 0){ //세션이 없을경우
            return null;
        }
        if(validSessionTime()) { //세션시간이 유효하면
            refreshSession(); //세션시간 갱신
            return attribute.get(key);
        }else{ //세션시간이 지났다면
            attribute.clear(); //속성값 초기화
            return null;
        }
    }

    private static boolean validSessionTime(){
        boolean result = false;
        if(makeDate + sessionTime >= System.currentTimeMillis ()){
            result = true;
        }
        return result;
    }
}
