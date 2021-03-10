package my.web.note.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 관련 유틸
 * 업로드한 파일의 저장 & 서버에 저장된 파일 삭제 등의 기능 제공
 */
public class FileService {

	/**
	 * 업로드 된 파일을 지정된 경로에 저장하고, 저장된 파일명을 리턴
	 * @param mfile 업로드된 파일
	 * @param path 저장할 경로
	 * @return 저장된 파일명
	 */
	public static String saveFile(MultipartFile mfile, String uploadPath) {
		//업로드된 파일이 없거나 크기가 0이면 저장하지 않고 null을 리턴
		if (mfile == null || mfile.isEmpty() || mfile.getSize() == 0) {
			return null;
		}
		// uploadPath = c:\boardfile 임의로 생각한 경로 지금 로직이랑은 아무런 상관이 없다.
		//저장 폴더가 없으면 생성, File 자바에서의 객체 이다.
		File path = new File(uploadPath);
		// uploadPathd에는 지정된 파일 저장 경로가 있다
		if (!path.isDirectory()) { 
		//이 구문은 path경로에 디렉토리가 있냐? 근데 앞에 !가 붙었으니 만약 없으면 true가 되고 있으면  false이 된다
			path.mkdirs(); // 지정된 경로에 폴더가 없을 때 그 path경로에 디렉토리를 만들어라 라는 메서드
		}
		
		//원본 파일명(test.jpg), 이것도 임의로 생각한 원본 파일며 이다. 쉽게 이해하기 위해서 만듬
		//getOriginalFilename()은 MultipartFile 객체가 가지고 있는 method 이다.
		String originalFilename = mfile.getOriginalFilename();
		
		//저장할 파일명을 오늘 날짜의 년월일로 생성, DB에서 to_char같은 형식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//20200810로 저장
		String savedFilename = sdf.format(new Date());
		
		//원본 파일의 확장자를 담아줄 변수 ext
		String ext;
		//lastIndexOf 뒤에서부터 . 을 잦는 메서드 test.jpg를 기준으로 값은 4가 나오게 된다.
		int lastIndex = originalFilename.lastIndexOf('.');
		//확장자가 없는 경우
		if (lastIndex == -1) {
			ext = "";
		}
		//확장자가 있는 경우
		else {
			// "." + "jpg" -> ".jpg"
			ext = "." + originalFilename.substring(lastIndex + 1);
		}

		//저장할 전체 경로를 포함한 File 객체
		File serverFile = null;
		
		//같은 이름의 파일이 있는 경우의 처리
		while (true) {
			//"c:/boardfile" +"/"+ "20200810" + ".jpg" -> "c:/boardfile/20200810.jpg"
			serverFile = new File(uploadPath + "/" + savedFilename + ext);
			//같은 이름의 파일이 없으면 나감.
			if ( !serverFile.isFile()) break;	
			//같은 이름의 파일이 있으면 이름 뒤에 long 타입의 시간정보를 덧붙임.
			//"20200810" + "11"11:00" -> ms으로 바뀌어 long타입으로 바뀐 시간이 나온다 -> "202008101256856854"
			savedFilename = savedFilename + new Date().getTime();	
		}		
		// 원본파일명 = test.jpg
		// 저장파일명 = 202008101256856854.jpg
		
		
		//파일 저장
		try {
			//파일객체에 tragsferTo 메소드를 실행할때 저장할 파일 객체를 매개변수로 전달, 파일저장
			//물리적인 저장
			mfile.transferTo(serverFile);
		} catch (Exception e) {
			savedFilename = null;
			e.printStackTrace();
		}
		//논리적인 저장( DB에 xxx파일을 저장 했다 )
		return savedFilename + ext;
	}
	
	/**
	 * 서버에 저장된 파일의 전체 경로를 전달받아, 해당 파일을 삭제
	 * @param fullpath 삭제할 파일의 경로
	 * @return 삭제 여부
	 */
	public static boolean deleteFile(String fullpath) {
		//파일 삭제 여부를 리턴할 변수
		boolean result = false;
		
		//전달된 전체 경로로 File객체 생성
		File delFile = new File(fullpath);
		
		//해당 파일이 존재하면 삭제
		if (delFile.isFile()) {
			delFile.delete();
			result = true;
		}
		
		return result;
	}
}
