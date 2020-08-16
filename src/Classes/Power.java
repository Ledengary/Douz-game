package Classes;
import Main.*;

public class Power {

	public static String pow(String x, String y) {
		StringBuilder temp = new StringBuilder();
		temp = temp.append(x);
		StringBuilder i = new StringBuilder();
		String tempBack = "";
		for (i = i.insert(0, "1"); !checkBigger(i.toString(), y); i = i.replace(0, i.length(),
				add(i.toString(), "1"))) {
			tempBack = temp.toString();
			temp = temp.replace(0, temp.length(), multiply(temp.toString(), x));
		}
		return tempBack;
	}

	public static boolean checkBigger(String x, String y) {
		int xCharInt = 0;
		int yCharInt = 0;
		if (x.length() > y.length()) {
			return true;
		} else if (x.length() < y.length()) {
			return false;
		} else if (x.length() == y.length()) {
			for (int i = 0; i < x.length(); i++) {
				xCharInt = x.charAt(i) - '0';
				yCharInt = y.charAt(i) - '0';
				if (xCharInt > yCharInt) {
					return true;
				} else if (xCharInt < yCharInt) {
					return false;
				}
			}
		}
		return false;
	}

	public static String add(String x, String y) {
		// az inja ta akhar in if tamami condition haye lazem baraye check kardan ine ke
		// nakone yeki az input ha manfi bashe...
		if (x.charAt(0) == '-' || y.charAt(0) == '-') {
			if (x.charAt(0) == '-' && y.charAt(0) != '-') {
				if (x.charAt(1) == y.charAt(0)) {
					return "0";
				} else {
					if (checkBigger(x.substring(1, x.length()), y)) {
						return "-" + sub(x.substring(1, x.length()), y);
					} else if (!checkBigger(x.substring(1, x.length()), y)) {
						return sub(y, x.substring(1, x.length()));
					}
				}
			} else if (x.charAt(0) != '-' && y.charAt(0) == '-') {
				if (y.charAt(1) == x.charAt(0)) {
					return "0";
				} else {
					if (checkBigger(x, y.substring(1, y.length()))) {
						return sub(x, y.substring(1, y.length()));
					} else if (!checkBigger(x, y.substring(1, y.length()))) {
						return "-" + sub(y.substring(1, y.length()), x);
					}
				}
			} else if (x.charAt(0) == '-' && y.charAt(0) == '-') {
				return "-" + add(x.substring(1, x.length()), y.substring(1, y.length()));
			}
		}
		// ta inja :)
		// next if makes x to be higher number and y the lower number
		if (y.length() > x.length()) {
			String tmp = y;
			y = x;
			x = tmp;
		}
		// tempNum = when x is 1234 and y is 56, tempNum is 12 !
		String tempNum = "";
		if (x.length() != y.length()) {
			if (x.length() > y.length()) {
				for (int i = 0; i < Math.abs(x.length() - y.length()); i++) {
					tempNum += Character.toString(x.charAt(i));
				}
			} else {
				for (int i = 0; i < Math.abs(x.length() - y.length()); i++) {
					tempNum += Character.toString(y.charAt(i));
				}
			}
		}
		int carry = 0;
		int yCharToInt = 0;
		int xCharToInt = 0;
		// moveOn = is the param that saves the counter of i in the adding peocess;
		// refer to lines 188 and 189
		int moveOn = x.length() - 1;
		StringBuilder answer = new StringBuilder("");
		for (int j = y.length() - 1; j >= 0; j--) {
			for (int i = moveOn; i >= 0; i--) {
				yCharToInt = y.charAt(j) - '0';
				xCharToInt = x.charAt(i) - '0';
				if (xCharToInt + yCharToInt + carry >= 10) {
					// if it's the last part of adding, the number should be writen with its carry
					if (i != 0) {
						answer = answer.insert(0, Integer.toString((xCharToInt + yCharToInt + carry) - 10));
					} else {
						answer = answer.insert(0, Integer.toString((xCharToInt + yCharToInt + carry)));
					}
					carry = 1;
				} else {
					answer = answer.insert(0, Integer.toString((xCharToInt + yCharToInt + carry)));
					carry = 0;
				}
				moveOn = i - 1;
				break;
			}
		}
		// the adding peocess is finished

		// the answer that we have till here is without our tempNum, not let's add
		// tempNum to the answer
		if (x.length() != y.length()) {
			if (carry != 0) {
				tempNum = Integer.toString(Integer.parseInt(tempNum) + carry);
			}
			answer = answer.insert(0, tempNum);
		}
		return answer.toString();
	}

	public static String sub(String x, String y) {
		// az inja ta akhar in if tamami condition haye lazem baraye check kardan ine ke
		// nakone yeki az input ha manfi bashe...
		if (x.charAt(0) == '-' || y.charAt(0) == '-') {
			if (x.charAt(0) == '-' && y.charAt(0) != '-') {
				return "-" + add(x.substring(1, x.length()), y);
			} else if (x.charAt(0) != '-' && y.charAt(0) == '-') {
				return "-" + add(x, y.substring(1, y.length()));
			} else if (x.charAt(0) == '-' && y.charAt(0) == '-') {
				return add(x, y.substring(1, y.length()));
			}
		}
		// ta inja :)
		// normalornot is a variable to use as if the first number is bigger than the
		// second one or not, if not, then math library should be used as abs
		boolean normalSubOrNot = true;
		int xCharToInt = 0;
		int yCharToInt = 0;
		StringBuilder answer = new StringBuilder();
		StringBuilder yBuilder = new StringBuilder();
		String finalAnswer = "";
		// giveOrNot = hamoon moghei e ke 3 ro be 13 tabdil mikonim va yedoone az
		// baghali samte chapesh migirim
		boolean gotOrNot = false;
		// next if = check mikone ke aya adad aval bozorgtare az adad dovom ya na, age
		// are is normal age na is not normal
		if (x.length() < y.length()) {
			normalSubOrNot = false;
		}
		if (x.length() == y.length()) {
			for (int i = 0; i < x.length(); i++) {
				xCharToInt = x.charAt(i) - '0';
				yCharToInt = y.charAt(i) - '0';
				if (yCharToInt > xCharToInt) {
					normalSubOrNot = false;
					break;
				} else if (xCharToInt > yCharToInt) {
					break;
				}
			}
		}
		if (normalSubOrNot) {
			// this part of the code makes the y with the same digits of the x, for example
			// x is 33512 and y is 12, it makes y to 00012;
			yBuilder = yBuilder.insert(0, y);
			for (int i = 0; i < x.length() - y.length(); i++) {
				yBuilder = yBuilder.insert(0, 0);
			}
			y = yBuilder.toString();
			for (int i = x.length() - 1; i >= 0; i--) {
				xCharToInt = x.charAt(i) - '0';
				if (gotOrNot) {
					xCharToInt--;
					if (xCharToInt == -1) {
						xCharToInt = 9;
					} else {
						gotOrNot = false;
					}
				}
				yCharToInt = y.charAt(i) - '0';
				if (xCharToInt >= yCharToInt) {
					// it checks to avoid having a number like 0999 when you want to sub 1000 and 1
					// ! so it prints 999 instead of 0999
					if (xCharToInt - yCharToInt == 0) {
						if (i != 0) {
							answer = answer.insert(0, xCharToInt - yCharToInt);
						}
					} else {
						answer = answer.insert(0, xCharToInt - yCharToInt);
					}
				} else {
					xCharToInt += 10;
					if (!gotOrNot) {
						gotOrNot = true;
					}
					answer = answer.insert(0, xCharToInt - yCharToInt);
				}
			}
		} else {
			answer = answer.insert(0, sub(y, x));
			answer = answer.insert(0, "-");
		}
		// this part of the code avoids printing zeroes before, like printing 2 instead
		// of 002 when substracting 1025 from 1023
		int subFrom = 0;
		for (int i = 0; i < answer.toString().length(); i++) {
			if (answer.toString().charAt(i) != '0') {
				subFrom = i;
				break;
			}
			if (i == answer.toString().length() - 1) {
				answer = answer.replace(0, answer.toString().length(), "0");
			}
		}
		return answer.toString().substring(subFrom, answer.length());
	}

	public static String multiply(String x, String y) {
		// next if makes x to be higher number and y the lower number
		if (y.length() > x.length()) {
			String tmp = y;
			y = x;
			x = tmp;
		}
		// next if returns 0 if one of the inouts is 0 !
		if (x.equals("0") || y.equals("0")) {
			return "0";
		}
		int carry = 0;
		int yCharToInt = 0;
		int xCharToInt = 0;
		int overflowness = 0;
		// mulparts hamoon motaghayeri e ke vaghti zarb mikonim javabo pishesh ye sefr
		// mizarim, dobare zarb mikonim ye sefr dg mizari, in hamoone
		StringBuilder[] mulParts = new StringBuilder[y.length()];
		int mulPartsCounter = 0;
		// tempsum hamoon mulparts e vali type string dare ke dar akhar kar be add(x,y)
		// pass midimesh
		String[] tempSum = new String[y.length()];
		String finalAnswer = "";
		StringBuilder answer = new StringBuilder("");
		for (int j = y.length() - 1; j >= 0; j--) {
			for (int i = x.length() - 1; i >= 0; i--) {
				yCharToInt = y.charAt(j) - '0';
				xCharToInt = x.charAt(i) - '0';
				if ((xCharToInt * yCharToInt) + carry >= 10) {
					if (i != 0) {
						// if it's the last part of adding, the number should be writen with its
						// overflowness
						overflowness = (((xCharToInt * yCharToInt) + carry)
								- (((xCharToInt * yCharToInt) + carry) % 10)) / 10;
						answer = answer.insert(0,
								Integer.toString(((xCharToInt * yCharToInt) + carry) - (overflowness * 10)));
					} else {
						answer = answer.insert(0, Integer.toString(((xCharToInt * yCharToInt) + carry)));
					}
					carry = overflowness;
				} else {
					answer = answer.insert(0, Integer.toString(((xCharToInt * yCharToInt) + carry)));
					carry = 0;
				}
			}
			carry = 0;
			// the next if else baraye ine ke masalan ba do ta adad 123 va 45 agar avalin
			// bare dari 5(y) o dar 123(x) zarb mikoni javabesh o bedoon safr samte rastesh
			// bezar too mulpart aval. vali age avalin bar nist bebin mulpartscounter et
			// chande, bad be tedad oon samte rastesh sefr bezar ;)
			if (mulPartsCounter == 0) {
				mulParts[mulPartsCounter] = answer;
			} else {
				mulParts[mulPartsCounter] = answer;
				for (int i = 0; i < mulPartsCounter; i++) {
					mulParts[mulPartsCounter] = mulParts[mulPartsCounter].insert(mulParts[mulPartsCounter].length(),
							"0");
				}
			}
			// az oonjai ke tempsum hamoon mulparts e, mulparts o be string tabdil mikonim
			// bad mirizimesh to tempsum
			tempSum[mulPartsCounter] = mulParts[mulPartsCounter].toString();
			mulPartsCounter++;
			answer.setLength(0);
		}
		// hala tamam tempsum haro ba ham jam mikonim
		finalAnswer = tempSum[0];
		for (int i = 1; i < tempSum.length; i++) {
			finalAnswer = add(finalAnswer, tempSum[i]);
		}
		return finalAnswer;
	}

	public static String dividePanel(String x, String y) {
		if (x.length() > y.length()) {
			return divideXisBigger(x, y, true);
		} else if (x.length() < y.length()) {
			return divideYisBigger(x, y);
		} else if (x.length() == y.length()) {
			for (int i = 0; i < x.length(); i++) {
				if (x.charAt(i) - '0' > y.charAt(i) - '0') {
					return divideXisBigger(x, y, true);
				} else if (x.charAt(i) - '0' < y.charAt(i) - '0') {
					return divideYisBigger(x, y);
				}
			}
		}
		return "";
	}

	public static String divideXisBigger(String x, String y, boolean xIsBigOrNot) {
		// code generates the two logarithms of the two numbers given, and subtracts
		// them, and then the antilog of that subtraction is the answer !
		double logX = Math.log10(Double.parseDouble(x));
		double logY = Math.log10(Double.parseDouble(y));
		String answer = Double.toString(Math.pow(10, logX - logY));
		StringBuilder finalAnswer = new StringBuilder(answer);
		String[] answers = new String[2];
		// let's convert exponential to number ! (1e*11 == 1 * 10^11) :)
		if (answer.indexOf("E") != -1) {
			answers = answer.split("E");
			answers[0] = answer.substring(0, answer.indexOf("."))
					+ answer.substring(answer.indexOf(".") + 1, answer.indexOf("E"));
			finalAnswer = finalAnswer.replace(0, finalAnswer.length(), answers[0]);
			if (Integer.parseInt(answers[1]) + 1 != answers[0].length()) {
				finalAnswer = finalAnswer.insert(Integer.parseInt(answers[1]) + 1, ".");
			}
		}
		// xIsBigOrNot is a boolean that if it is true it means that devide panel had
		// send us here directly, if not it means that y was bigger and we were sent
		// here from the function divideYisBigger !
		if (xIsBigOrNot) {
			return roundUp(finalAnswer);
		} else {
			return finalAnswer.toString();
		}
	}

	public static String roundUp(StringBuilder x) {
		String finalAnswer = x.toString();
		String beforeDot = "";
		String afterDot = "";
		boolean hitDot = false;
		// split couldnt work :/ so i just splited them by myself
		for (int i = 0; i < finalAnswer.length(); i++) {
			if (finalAnswer.charAt(i) != '.' && !hitDot) {
				beforeDot += Character.toString(finalAnswer.charAt(i));
			} else {
				hitDot = true;
				if (finalAnswer.charAt(i) == '.') {
					i++;
				}
				afterDot += Character.toString(finalAnswer.charAt(i));
			}
		}
		// till here
		// let's round up the afterDot to two digits
		if (afterDot.length() > 1) {
			afterDot = Character.toString(afterDot.charAt(0)) + Character.toString(afterDot.charAt(1));
		} else {
			afterDot = Character.toString(afterDot.charAt(0)) + "0";
		}
		// rounded up
		// now let's round up the whole number
		if (Integer.parseInt(afterDot) > 50) {
			finalAnswer = add(beforeDot, "1");
		} else if (Integer.parseInt(afterDot) < 50) {
			finalAnswer = beforeDot;
		}
		// till here
		return finalAnswer;
	}

	public static String divideYisBigger(String x, String y) {
		return divideXisBigger(x, y, false);
	}
}
