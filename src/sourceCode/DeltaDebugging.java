package sourceCode;

import java.util.ArrayList;
import java.util.Collection;

public class DeltaDebugging {

	public static void main(String[] args) {
		
		String htmlPage = "<td align=left valign=top>" +
						   "<SELECT NAME=\"op sys\" MULTIPLE SIZE=7>" +
						   "<OPTION VALUE=\"All\">All<OPTION VALUE=\"Windows 3.1\">Windows 3.1"
						   + "<OPTION VALUE=\"Windows 95\">Windows 95<OPTION VALUE=\"Windows 98\">"
						   + "Windows 98<OPTION VALUE=\"Windows ME\">Windows ME<OPTION VALUE=\"Windows 2000\">"
						   + "Windows 2000<OPTION VALUE=\"Windows NT\">Windows NT<OPTION VALUE=\"Mac System 7\">"
						   + "Mac System 7<OPTION VALUE=\"Mac System 7.5\">Mac System 7.5<OPTION VALUE=\"Mac System 7.6.1\">"
						   + "Mac System 7.6.1<OPTION VALUE=\"Mac System 8.0\">Mac System 8.0<OPTION VALUE=\"Mac System 8.5\">"
						   + "Mac System 8.5<OPTION VALUE=\"Mac System 8.6\">Mac System 8.6<OPTION VALUE=\"Mac System 9.x\">Mac System 9.x"
						   + "<OPTION VALUE=\"MacOS X\">MacOS X<OPTION VALUE=\"Linux\">Linux<OPTION VALUE=\"BSDI\">BSDI<OPTION VALUE=\"FreeBSD\">"
						   + "FreeBSD<OPTION VALUE=\"NetBSD\">NetBSD<OPTION VALUE=\"OpenBSD\">OpenBSD<OPTION VALUE=\"AIX\">AIX<OPTION VALUE=\"BeOS\">"
						   + "BeOS<OPTION VALUE=\"HP-UX\">HP-UX<OPTION VALUE=\"IRIX\">IRIX<OPTION VALUE=\"Neutrino\">Neutrino<OPTION VALUE=\"OpenVMS\">"
						   + "OpenVMS<OPTION VALUE=\"OS/2\">OS/2<OPTION VALUE=\"OSF/1\">OSF/1<OPTION VALUE=\"Solaris\">Solaris<OPTION VALUE=\"SunOS\">"
						   + "SunOS<OPTION VALUE=\"other\">other</SELECT>"+ "</td>" + "<td align=left valign=top>" +"<SELECT NAME=\"priority\" MULTIPLE SIZE=7>"
						   + "<OPTION VALUE=\"--\">--<OPTION VALUE=\"P1\">P1<OPTION VALUE=\"P2\">P2<OPTION VALUE=\"P3\">P3<OPTION VALUE=\"P4\">"
						   + "P4<OPTION VALUE=\"P5\">P5</SELECT>" +"</td>" +"<td align=left valign=top>" +"<SELECT NAME=\"bug severity\" MULTIPLE SIZE=7>" 
						   + "<OPTION VALUE=\"blocker\">blocker<OPTION VALUE=\"critical\">critical<OPTION VALUE=\"major\">major<OPTION VALUE=\"normal\">"
						   + "normal<OPTION VALUE=\"minor\">minor<OPTION VALUE=\"trivial\">trivial<OPTION VALUE=\"enhancement\">enhancement</SELECT></tr></table>";
		
		
		/*System.out.println(doesTheTestFails("<SELECT NAME=\"priority\" MULTIPLE SIZE=7>"));
		System.out.println(doesTheTestFails("<SELECT NAME=\"priori"));
		System.out.println(doesTheTestFails("ty\" MULTIPLE SIZE=7>"));
		System.out.println(doesTheTestFails(htmlPage));*/
		
		String oneMinimalTest = runDeltaDebugging(htmlPage, 2);
		
		System.out.println(oneMinimalTest);

	}
	
	
	public static String runDeltaDebugging(String delta, int n){
		
		ArrayList<Test> tests = (ArrayList<Test>) splitTest(delta, n);
		for(Test test: tests){
			if(doesTheTestFails(test.testString)){
				if(test.isDelta){
					return runDeltaDebugging(test.testString, 2);
				} else {
					return runDeltaDebugging(test.testString, n-1);
				}
			}
		}
		
		
		//check if granularity can be redefined
		if(delta.length() >= (n*2)){
			return runDeltaDebugging(delta, n*2);
		} else {
			return delta;
		}
	}
	
	
	private static boolean doesTheTestFails(String test){
		if(test.matches(".*<SELECT.*>.*"))
			return true;
		else
			return false;
	}
	
	
	private static Collection<Test> splitTest(String delta, int n){
		Collection<Test> result = new ArrayList<Test>();
		
		int testSize = delta.length()/n;
		
		int currentIndex = 0;
		while(currentIndex<(delta.length()-testSize)){
			Test deltaToAdd = new Test();
			deltaToAdd.testString = delta.substring(currentIndex, currentIndex+testSize);
			deltaToAdd.isDelta = true;
			if(!result.contains(deltaToAdd))
				result.add(deltaToAdd);
			
			Test nablaToAdd = new Test();
			nablaToAdd.testString = delta.substring(0,currentIndex) + delta.substring(currentIndex+testSize);
			nablaToAdd.isDelta = false;
			if(!result.contains(nablaToAdd))
				result.add(nablaToAdd);
			
			currentIndex+=testSize;
		}
		
		//If some characters are left
		if(currentIndex<delta.length()){
			Test deltaToAdd = new Test();
			deltaToAdd.testString = delta.substring(currentIndex);
			deltaToAdd.isDelta = true;
			if(!result.contains(deltaToAdd))
				result.add(deltaToAdd);
			
			Test nablaToAdd = new Test();
			nablaToAdd.testString = delta.substring(0,currentIndex);
			nablaToAdd.isDelta = false;
			if(!result.contains(nablaToAdd))
				result.add(nablaToAdd);
		}
		
		return result;
	}

}
