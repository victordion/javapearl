package learning;
import java.io.*;
import java.net.*;
public class SourceViewer2 {
	public static void main (String[] args) {
		String add;
		if (args. length > 0) {
			add = args[0];
		}
		else{
			add = "http://google.com";	
		}
		try {
			// Open the URLConnection for reading
			URL u = new URL(add );
			URLConnection uc = u. openConnection();
			try (InputStream raw = uc. getInputStream()) { // autoclose
				InputStream buffer = new BufferedInputStream(raw);
				// chain the InputStream to a Reader
				Reader reader = new InputStreamReader(buffer);
				int c;
				while ((c = reader. read()) != - 1) {
					System. out. print((char) c);
				}
			}
		} catch (MalformedURLException ex) {
			System. err. println(add + " is not a parseable URL" );
		} catch (IOException ex) {
			System. err. println(ex);
		}

	}
}
