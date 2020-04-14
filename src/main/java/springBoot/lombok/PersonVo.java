package springBoot.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author li_hhui
 * @date:2020年4月14日
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonVo {

	private int age;
	private String name;
	private String address;
	
}
