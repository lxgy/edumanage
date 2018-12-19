package com.seehope.core.page;

import java.util.ArrayList;
import java.util.List;

public class Sort {
	protected String name;
	protected boolean isAsc;
	private List<Sort> mSorts;

	public static Sort parese(String str) {
		if ((str == null) || (str.length() < 1)) {
			return null;
		}
		String[] strs = str.split(",");
		if ((strs != null) && (strs.length > 0)) {
			Sort s = null;
			boolean bAsc = true;
			int nIdx = 0;
			String[] arrayOfString1;
			int j = (arrayOfString1 = strs).length;
			for (int i = 0; i < j; i++) {
				String item = arrayOfString1[i];

				item = item.trim();
				nIdx = item.indexOf(' ');
				if (nIdx > 0) {
					if ("desc".equalsIgnoreCase(item.substring(1 + nIdx).trim())) {
						bAsc = false;
					}
					item = item.substring(0, nIdx).trim();
				}
				if (s == null) {
					s = new Sort(item, bAsc);
				} else {
					s.and(new Sort(item, bAsc));
				}
			}
			return s;
		}
		return null;
	}

	public Sort(String name, boolean asc) {
		this.name = name;
		this.isAsc = asc;
	}

	public Sort(boolean asc, String... names) {
		if (names == null) {
			return;
		}
		this.name = names[0];
		this.isAsc = asc;
		if (names.length > 1) {
			for (int i = 1; i < names.length; i++) {
				and(new Sort(names[i], asc));
			}
		}
	}

	public Sort and(Sort sort) {
		if (this.mSorts == null) {
			this.mSorts = new ArrayList<>(4);
		}
		this.mSorts.add(sort);
		return this;
	}

	public String toQL() {
		StringBuilder sb = new StringBuilder(64);
		sb.append(" order by ");
		toTheQL(sb, null);
		return sb.toString();
	}

	public String toQL(String alias) {
		StringBuilder sb = new StringBuilder(64);
		sb.append(" order by ");
		toTheQL(sb, alias);
		return sb.toString();
	}

	private void toTheQL(StringBuilder ql, String alias) {
		if ((alias != null) && (alias.length() > 0)) {
			ql.append(alias).append('.');
		}
		ql.append(this.name);
		if (!this.isAsc) {
			ql.append(" DESC");
		}
		if (this.mSorts != null) {
			for (Sort item : this.mSorts) {
				ql.append(',');
				item.toTheQL(ql, alias);
			}
		}
	}

	protected List<Sort> getAll() {
		ArrayList<Sort> ret = new ArrayList<>();
		ret.add(this);
		if (this.mSorts != null) {
			ret.addAll(this.mSorts);
		}
		return ret;
	}
}
