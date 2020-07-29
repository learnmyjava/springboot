package springBoot.lombok;

import org.junit.jupiter.api.Test;

/**
 * @author li_hhui
 * @date:2020年4月14日
 * @version:
 */
public class LomboxTest {
	
	@Test
	public void test(){
		//安装lombok后  clean 项目 重启
		PersonVo personVo = new PersonVo();
		personVo.setAddress("ddd");
		System.out.println(personVo.toString());
		PersonVo personVo2 = new PersonVo(0, "tom", "dafd");
		System.out.println(personVo2.toString());
	}

}
