import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
/*
   1. 아래와 같은 명령어를 입력하면 컴파일이 이루어져야 하며, Solution1 라는 이름의 클래스가 생성되어야 채점이 이루어집니다.
       javac Solution1.java -encoding UTF8


   2. 컴파일 후 아래와 같은 명령어를 입력했을 때 여러분의 프로그램이 정상적으로 출력파일 output4.txt 를 생성시켜야 채점이 이루어집니다.
       java Solution1

   - 제출하시는 소스코드의 인코딩이 UTF8 이어야 함에 유의 바랍니다.
   - 수행시간 측정을 위해 다음과 같이 time 명령어를 사용할 수 있습니다.
       time java Solution1
   - 일정 시간 초과시 프로그램을 강제 종료 시키기 위해 다음과 같이 timeout 명령어를 사용할 수 있습니다.
       timeout 0.5 java Solution1   // 0.5초 수행
       timeout 1 java Solution1     // 1초 수행
 */

class Solution4 {
	static final int max_n = 1000000;

	static int n;
	static int[][] A = new int[max_n][3];
	static int Answer;

	public static void main(String[] args) throws Exception {
		/*
		   동일 폴더 내의 input4.txt 로부터 데이터를 읽어옵니다.
		   또한 동일 폴더 내의 output4.txt 로 정답을 출력합니다.
		 */
		BufferedReader br = new BufferedReader(new FileReader("input4.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output4.txt");

		/*
		   10개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */
		for (int test_case = 1; test_case <= 10; test_case++) {

			stk = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stk.nextToken());
			for (int i = 0; i < 3; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					A[j][i] = Integer.parseInt(stk.nextToken());
				}
			}

			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
			   이 부분에서 여러분의 알고리즘이 수행됩니다.
			   문제의 답을 계산하여 그 값을 Answer에 저장하는 것을 가정하였습니다.
			 */
			/////////////////////////////////////////////////////////////////////////////////////////////
			Answer = 0;

			int[][] w = new int[n][6];
			for (int i=0; i<n; i++){
				for (int p=0; p<6; p++){
					w[i][p] = calcScore(A[i],p);
				}
			}

			int[][] comparable = new int[][] {
				{3, 4},
				{2, 5},
				{1, 5},
				{0, 4},
				{0, 3},
				{1, 2}
			};

			int[][] m = new int[n][6];
			for (int p=0; p<6; p++){
				m[0][p] = w[0][p];
			}
			for (int i=1; i<n; i++){
				for (int p=0; p<6; p++){
					m[i][p] = Math.max(m[i-1][comparable[p][0]], m[i-1][comparable[p][1]]) + w[i][p];
				}
			}

			Answer = Integer.MIN_VALUE;
			for (int p=0; p<6; p++){
				if (Answer < m[n-1][p]) Answer = m[n-1][p];
			}

			// output4.txt로 답안을 출력합니다.
			pw.println("#" + test_case + " " + Answer);
			/*
			   아래 코드를 수행하지 않으면 여러분의 프로그램이 제한 시간 초과로 강제 종료 되었을 때,
			   출력한 내용이 실제로 파일에 기록되지 않을 수 있습니다.
			   따라서 안전을 위해 반드시 flush() 를 수행하시기 바랍니다.
			 */
			pw.flush();
		}

		br.close();
		pw.close();
	}

	private static int calcScore(int[] s, int p){
		// p1 = + 0 -
		// p2 = + - 0
		// p3 = 0 + -
		// p4 = 0 - +
		// p5 = - + 0
		// p6 = - 0 +
		if (p==0) return s[0]-s[2];
		if (p==1) return s[0]-s[1];
		if (p==2) return s[1]-s[2];
		if (p==3) return -s[1]+s[2];
		if (p==4) return -s[0]+s[1];
		else return -s[0]+s[2];
	}
}

