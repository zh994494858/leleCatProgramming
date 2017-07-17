package cc.lelecat.framework;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Created by ACat on 2016/9/30.
 * ACat i lele.
 */
public class MySQL5InnoDBUTF8Dialect extends MySQL5InnoDBDialect {
	@Override
	public String getTableTypeString() {
		return super.getTableTypeString() + " charset=utf8";
	}
}
