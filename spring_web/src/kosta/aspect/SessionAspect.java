package kosta.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class SessionAspect {
	
	@Around("execution(public * kosta.controller.*.*do(..))") // kosta.controller패키지 안에 do로 끝나는 메서드
	public String sessionCheck(ProceedingJoinPoint joinPoint)throws Throwable{
		// session 유무를 체크 ?  Session객체
		Object[] obj = joinPoint.getArgs();//controller로 넘어오는 parameter를 배열로 다 가져온다.\
		HttpServletRequest request = (HttpServletRequest)obj[0];
		
		HttpSession session = request.getSession();
		
		String name = (String)session.getAttribute("name");
		String view = "session/session_fail";
		
		try {
			if(name == null){
				throw new Exception("no session");
			}
			view = (String)joinPoint.proceed();//session_do() 호출
		} catch (Exception e) {
			return view;
		}
		
		return view;
	}
}
