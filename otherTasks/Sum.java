public class Sum {
	public static void main(String[] args) {
		int ans = 0;
		int len = 0, first = 0;
		for (int i = 0; i < args.length; i++) {
			String str = args[i];
			for (int j = 0; j < str.length(); j++){
				if (!Character.isWhitespace(str.charAt(j))) {
						if (j == 0 || Character.isWhitespace(str.charAt(j - 1))) {
							first = j;
						}
				} if (!Character.isWhitespace(str.charAt(j))) {
					  len++;
				} if (!Character.isWhitespace(str.charAt(j))) {
					if (j == str.length() - 1 || Character.isWhitespace(str.charAt(j + 1))) {
						ans += Integer.parseInt(str.substring(first, first + len));
						len = 0;
						first = 0;
					}
				}
			}	
		}
		System.out.println(ans);
	}
}