public class BowlingGame {

    public int getBowlingScore(String bowlingCode) {
        int score = 0;
		int numSplit = 0;
		int frameNum = 0;
		String[] sArr = bowlingCode.split("\\|");
		//System.out.println(Arrays.toString(sArr));
		for(int i = 0; i < 10; i++){//计算每一个frame，然后相加
			frameNum++;//正在计算第几个格
			//System.out.println("第"+i+"个frame的得分是："+colFrameScore(frameNum, i, sArr));
			score = score + colFrameScore(frameNum, i, sArr);
		}
        return score;
    }
    private static int colFrameScore(int frameNum, int i, String[] sArr) {
		// TODO Auto-generated method stub
		int frameScore = 0;
//		System.out.println(sArr[i].equals("X"));
		if(frameNum < 9 || ((frameNum == 9) && (!sArr[i].equals("X"))) || ((frameNum == 10) && (!sArr[i].equals("X")) && (!Pattern.matches("\\d/", sArr[i])))){
			if(sArr[i].equals("X")){//如果是strike，这一格的分数等于 10 加上接下来两球击倒的瓶数的总和。
				if(sArr[i + 1].equals("X")){
					frameScore = 10 + 10 + nextBollScore(i + 2, sArr,1);
//					System.out.println(frameScore);
				}
				else{
					frameScore = 10 + nextBollScore(i + 1, sArr,1) + nextBollScore(i + 1, sArr,2);
				}	
			}
			if(Pattern.matches("\\d/", sArr[i])){//如果是spare，这一格的分数等于 10 加上接下来一个球击倒的瓶数。
				frameScore = 10 + nextBollScore(i + 1, sArr,1);
			}
			if(Pattern.matches("-\\d", sArr[i])){
				frameScore = Integer.parseInt(String.valueOf(sArr[i].charAt(1)));
			}
			if(Pattern.matches("\\d-", sArr[i])){
				frameScore = Integer.parseInt(String.valueOf(sArr[i].charAt(0)));
			}
		}
		if((frameNum == 9) && (sArr[i].equals("X"))){
			if(!sArr[i + 1].equals("X")){
				frameScore = 10 + nextBollScore(i + 1, sArr,1) + nextBollScore(i + 1, sArr, 2);
			}
			if(sArr[i + 1].equals("X")){
				frameScore = 10 + 10 + nextBollScore(i + 3, sArr,1);
			}
		}
		if((frameNum == 10) && (sArr[i].equals("X"))){
			frameScore = 10 + nextBollScore(i + 2, sArr,1) + nextBollScore(i + 2, sArr,2);
			
		}
		if((frameNum == 10) && (Pattern.matches("\\d/", sArr[i]))){
			frameScore = 10 + nextBollScore(i + 2, sArr,1);
		}
		return frameScore;
	}
	private static int nextBollScore(int i, String[] sArr, int num) {
		// TODO Auto-generated method stub
		if(Pattern.matches("X|XX", sArr[i])){
			return 10;
		}
		if(Pattern.matches("X\\w", sArr[i])){
			if(num == 1){
				return 10;
			}
			if(num == 2){
				return Integer.parseInt(String.valueOf(sArr[i].charAt(1)));
			}
		}
		if(Pattern.matches("\\d/", sArr[i])){
			if(num == 1){
				return Integer.parseInt(String.valueOf(sArr[i].charAt(0)));
			}
			if(num == 2){
				return 10 - Integer.parseInt(String.valueOf(sArr[i].charAt(0)));
			}
		}
		if(Pattern.matches("-\\d", sArr[i])){
			if(num == 1){
				return 0;
			}
			if(num == 2){
				return Integer.parseInt(String.valueOf(sArr[i].charAt(1)));
			}
		}
		if(Pattern.matches("\\d-", sArr[i])){
			if(num == 1){
				return Integer.parseInt(String.valueOf(sArr[i].charAt(0)));
			}
			if(num == 2){
				return 0;
			}
		}
		if(Pattern.matches("\\d{1,2}?", sArr[i])){
			if(num == 1){
				return Integer.parseInt(String.valueOf(sArr[i].charAt(0)));
			}
			if(num == 2){
				return Integer.parseInt(String.valueOf(sArr[i].charAt(1)));
			}
		}
		return 0;	
	}
}
