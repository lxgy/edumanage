package com.seehope.dao.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class QueryWhere {
	static final int OP_WHERE = 1;
	static final int OP_EQ = 2;
	static final int OP_LIKEAll = 3;
	static final int OP_NOT_LIKEAll = 4;
	static final int OP_IN = 5;
	static final int OP_NOT_IN = 6;
	static final int OP_LIKE = 7;
	static final int OP_NOT_LIKE = 8;
	static final int OP_CUSTOM = 9;
	static final int OP_IS_NULL = 16;
	static final int OP_IS_NOT_NULL = 17;
	static final int OP_MASK = 4095;
	static final int OP_AND = 0;
	static final int OP_OR = 4096;
	private List<String> attributes;
	private List<Object> values;
	private List<Integer> operations;

	public QueryWhere() {
		this.attributes = new ArrayList(8);
		this.values = new ArrayList(8);
		this.operations = new ArrayList(8);
	}

	public QueryWhere(Map<String, Object> items) {
		this();
		and(items);
	}

	public boolean hasWhere() {
		return this.attributes.size() > 0;
	}

	public QueryWhere and(QueryWhere where) {
		if ((where == null) || (!where.hasWhere())) {
			return this;
		}
		this.attributes.add("");
		this.values.add(where);
		this.operations.add(Integer.valueOf(1));
		return this;
	}

	public QueryWhere or(QueryWhere where) {
		if ((where == null) || (!where.hasWhere())) {
			return this;
		}
		this.attributes.add("");
		this.values.add(where);
		this.operations.add(Integer.valueOf(4097));
		return this;
	}

	public QueryWhere and(String attribute, Object value) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		this.attributes.add(attribute);
		this.values.add(value);
		this.operations.add(Integer.valueOf(2));
		return this;
	}

	public QueryWhere or(String attribute, Object value) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		this.attributes.add(attribute);
		this.values.add(value);
		this.operations.add(Integer.valueOf(4098));
		return this;
	}

	public QueryWhere and(String attribute, String op, Object value) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		op = " " + op + " ";
		this.attributes.add(attribute + op);
		this.values.add(value);
		this.operations.add(Integer.valueOf(9));
		return this;
	}

	public QueryWhere or(String attribute, String op, Object value) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		op = " " + op + " ";
		this.attributes.add(attribute + op);
		this.values.add(value);
		this.operations.add(Integer.valueOf(4105));
		return this;
	}

	public QueryWhere and(Map<String, Object> items) {
		if ((items == null) || (items.size() < 1)) {
			return this;
		}
		for (String key : items.keySet()) {
			if (items.get(key) != null) {
				this.attributes.add(key);
				this.values.add(items.get(key));
				this.operations.add(Integer.valueOf(2));
			}
		}
		return this;
	}

	public QueryWhere or(Map<String, Object> items) {
		if ((items == null) || (items.size() < 1)) {
			return this;
		}
		for (String key : items.keySet()) {
			if (items.get(key) != null) {
				this.attributes.add(key);
				this.values.add(items.get(key));
				this.operations.add(Integer.valueOf(4098));
			}
		}
		return this;
	}

	public QueryWhere and(String[] attributes, Object[] values) {
		if ((attributes == null) || (attributes.length < 1)) {
			return this;
		}
		if ((values == null) || (attributes.length != attributes.length)) {
			return this;
		}
		int nCount = attributes.length;
		for (int i = 0; i < nCount; i++) {
			if (values[i] != null) {
				this.attributes.add(attributes[i]);
				this.values.add(values[i]);
				this.operations.add(Integer.valueOf(2));
			}
		}
		return this;
	}

	public QueryWhere or(String[] attributes, Object[] values) {
		if ((attributes == null) || (attributes.length < 1)) {
			return this;
		}
		if ((values == null) || (attributes.length != attributes.length)) {
			return this;
		}
		int nCount = attributes.length;
		for (int i = 0; i < nCount; i++) {
			if (values[i] != null) {
				this.attributes.add(attributes[i]);
				this.values.add(values[i]);
				this.operations.add(Integer.valueOf(4098));
			}
		}
		return this;
	}

	public QueryWhere and(Collection<String> attributes, Collection<Object> values) {
		if ((attributes == null) || (attributes.size() < 1)) {
			return this;
		}
		if ((values == null) || (attributes.size() != attributes.size())) {
			return this;
		}
		int nCount = attributes.size();
		this.attributes.addAll(attributes);
		this.values.addAll(values);
		for (int i = 0; i < nCount; i++) {
			this.operations.add(Integer.valueOf(2));
		}
		return this;
	}

	public QueryWhere or(Collection<String> attributes, Collection<Object> values) {
		if ((attributes == null) || (attributes.size() < 1)) {
			return this;
		}
		if ((values == null) || (attributes.size() != attributes.size())) {
			return this;
		}
		int nCount = attributes.size();
		this.attributes.addAll(attributes);
		this.values.addAll(values);
		for (int i = 0; i < nCount; i++) {
			this.operations.add(Integer.valueOf(4098));
		}
		return this;
	}

	public QueryWhere andNull(String attribute) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		this.attributes.add(attribute);
		this.operations.add(Integer.valueOf(16));
		return this;
	}

	public QueryWhere orNull(String attribute) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		this.attributes.add(attribute);
		this.operations.add(Integer.valueOf(4112));
		return this;
	}

	public QueryWhere andNotNull(String attribute) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		this.attributes.add(attribute);
		this.operations.add(Integer.valueOf(17));
		return this;
	}

	public QueryWhere orNotNull(String attribute) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		this.attributes.add(attribute);
		this.operations.add(Integer.valueOf(4113));
		return this;
	}

	public QueryWhere likeAll(boolean and, String attribute, String value) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		this.attributes.add(attribute);
		this.values.add(value);
		if (and) {
			this.operations.add(Integer.valueOf(3));
		} else {
			this.operations.add(Integer.valueOf(4099));
		}
		return this;
	}

	public QueryWhere likeAll(String attribute, String value) {
		return likeAll(true, attribute, value);
	}

	public QueryWhere andLikeAll(String attribute, String value) {
		return likeAll(true, attribute, value);
	}

	public QueryWhere orLikeAll(String attribute, String value) {
		return likeAll(false, attribute, value);
	}

	public QueryWhere notLikeAll(boolean and, String attribute, String value) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		this.attributes.add(attribute);
		this.values.add(value);
		if (and) {
			this.operations.add(Integer.valueOf(4));
		} else {
			this.operations.add(Integer.valueOf(4100));
		}
		return this;
	}

	public QueryWhere notLikeAll(String attribute, String value) {
		return notLikeAll(true, attribute, value);
	}

	public QueryWhere andNotLikeAll(String attribute, String value) {
		return notLikeAll(true, attribute, value);
	}

	public QueryWhere orNotLikeAll(String attribute, String value) {
		return notLikeAll(false, attribute, value);
	}

	public QueryWhere like(boolean and, String attribute, String value) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		this.attributes.add(attribute);
		this.values.add(value);
		if (and) {
			this.operations.add(Integer.valueOf(7));
		} else {
			this.operations.add(Integer.valueOf(4103));
		}
		return this;
	}

	public QueryWhere notLike(boolean and, String attribute, String value) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		this.attributes.add(attribute);
		this.values.add(value);
		if (and) {
			this.operations.add(Integer.valueOf(8));
		} else {
			this.operations.add(Integer.valueOf(4104));
		}
		return this;
	}

	public QueryWhere in(boolean and, String attribute, Collection<? extends Object> values) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		this.attributes.add(attribute);
		this.values.add(values);
		if (and) {
			this.operations.add(Integer.valueOf(5));
		} else {
			this.operations.add(Integer.valueOf(4101));
		}
		return this;
	}

	public QueryWhere andIn(String attribute, Collection<? extends Object> values) {
		return in(true, attribute, values);
	}

	public QueryWhere orIn(String attribute, Collection<? extends Object> values) {
		return in(false, attribute, values);
	}

	public QueryWhere in(boolean and, String attribute, Object... values) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		ArrayList<Object> items = new ArrayList(values.length);
		Object[] arrayOfObject;
		int j = (arrayOfObject = values).length;
		for (int i = 0; i < j; i++) {
			Object item = arrayOfObject[i];
			if (item.getClass().isArray()) {
				int[] arrayOfInt;
				int m = (arrayOfInt = (int[]) item).length;
				for (int k = 0; k < m; k++) {
					int v = arrayOfInt[k];
					items.add(Integer.valueOf(v));
				}
			} else {
				items.add(item);
			}
		}
		in(and, attribute, items);
		return this;
	}

	public QueryWhere andIn(String attribute, Object... values) {
		return in(true, attribute, values);
	}

	public QueryWhere orIn(String attribute, Object... values) {
		return in(false, attribute, values);
	}

	public QueryWhere andIn(String attribute, int... values) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		ArrayList<Integer> items = new ArrayList(values.length);
		int[] arrayOfInt;
		int j = (arrayOfInt = values).length;
		for (int i = 0; i < j; i++) {
			int item = arrayOfInt[i];
			items.add(Integer.valueOf(item));
		}
		in(true, attribute, items);
		return this;
	}

	public QueryWhere orIn(String attribute, int... values) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		ArrayList<Integer> items = new ArrayList(values.length);
		int[] arrayOfInt;
		int j = (arrayOfInt = values).length;
		for (int i = 0; i < j; i++) {
			int item = arrayOfInt[i];
			items.add(Integer.valueOf(item));
		}
		in(false, attribute, items);
		return this;
	}

	public QueryWhere notIn(boolean and, String attribute, Collection<? extends Object> values) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		this.attributes.add(attribute);
		this.values.add(values);
		if (and) {
			this.operations.add(Integer.valueOf(6));
		} else {
			this.operations.add(Integer.valueOf(4102));
		}
		return this;
	}

	public QueryWhere andNotIn(String attribute, Collection<? extends Object> values) {
		return notIn(true, attribute, values);
	}

	public QueryWhere orNotIn(String attribute, Collection<? extends Object> values) {
		return notIn(false, attribute, values);
	}

	public QueryWhere notIn(boolean and, String attribute, Object... values) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		ArrayList<Object> items = new ArrayList(values.length);
		Object[] arrayOfObject;
		int j = (arrayOfObject = values).length;
		for (int i = 0; i < j; i++) {
			Object item = arrayOfObject[i];
			if (item.getClass().isArray()) {
				int[] arrayOfInt;
				int m = (arrayOfInt = (int[]) item).length;
				for (int k = 0; k < m; k++) {
					int v = arrayOfInt[k];
					items.add(Integer.valueOf(v));
				}
			} else {
				items.add(item);
			}
		}
		notIn(and, attribute, items);
		return this;
	}

	public QueryWhere andNotIn(String attribute, Object... values) {
		return notIn(true, attribute, values);
	}

	public QueryWhere orNotIn(String attribute, Object... values) {
		return notIn(false, attribute, values);
	}

	public QueryWhere andNotIn(String attribute, Number... values) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		ArrayList<Number> items = new ArrayList(values.length);
		Number[] arrayOfNumber;
		int j = (arrayOfNumber = values).length;
		for (int i = 0; i < j; i++) {
			Number item = arrayOfNumber[i];
			items.add(item);
		}
		notIn(true, attribute, items);
		return this;
	}

	public QueryWhere orNotIn(String attribute, Number... values) {
		if ((attribute == null) || (attribute.length() < 1)) {
			return this;
		}
		ArrayList<Number> items = new ArrayList(values.length);
		Number[] arrayOfNumber;
		int j = (arrayOfNumber = values).length;
		for (int i = 0; i < j; i++) {
			Number item = arrayOfNumber[i];
			items.add(item);
		}
		notIn(false, attribute, items);
		return this;
	}

	public String toQL(String alias) {
		return toQL(alias, 1);
	}

	public String toQL(String alias, int idx) {
		StringBuilder sb = new StringBuilder(128);
		toQL(sb, alias, idx);
		return sb.toString();
	}

	public int toQL(StringBuilder ql, String alias, int idx) {
		int nCount = this.attributes.size();
		if (nCount < 1) {
			return 0;
		}
		int nIdx = idx;
		for (int i = 0; i < nCount; i++) {
			int nOp = ((Integer) this.operations.get(i)).intValue();
			if (i > 0) {
				if (4096 == (0x1000 & nOp)) {
					ql.append(" or ");
				} else {
					ql.append(" and ");
				}
			} else {
				ql.append(' ');
			}
			nOp &= 0xFFF;
			if ((alias != null) && (1 != nOp)) {
				ql.append(alias).append('.');
			}
			if (2 == nOp) {
				ql.append((String) this.attributes.get(i)).append("=?" + nIdx);
				nIdx++;
			} else if ((3 == nOp) || (7 == nOp)) {
				ql.append((String) this.attributes.get(i)).append(" like ?" + nIdx);
				nIdx++;
			} else if ((4 == nOp) || (8 == nOp)) {
				ql.append((String) this.attributes.get(i)).append(" not like ?" + nIdx);
				nIdx++;
			} else if ((5 == nOp) || (6 == nOp)) {
				if (5 == nOp) {
					ql.append((String) this.attributes.get(i)).append(" in(?" + nIdx);
				} else {
					ql.append((String) this.attributes.get(i)).append(" not in(?" + nIdx);
				}
				ql.append(')');
				nIdx++;
			} else if (1 == nOp) {
				QueryWhere w = (QueryWhere) this.values.get(i);
				ql.append('(');
				nIdx += w.toQL(ql, alias, nIdx);
				ql.append(')');
			} else if (9 == nOp) {
				ql.append((String) this.attributes.get(i)).append("?" + nIdx);
				nIdx++;
			} else if (16 == nOp) {
				ql.append((String) this.attributes.get(i)).append(" is null");
			} else if (17 == nOp) {
				ql.append((String) this.attributes.get(i)).append(" is not null");
			}
		}
		return nIdx - idx;
	}

	public int toParameters(Query query) {
		return toParameters(1, query);
	}

	public int toParameters(int idx, Query query) {
		int nCount = this.values.size();
		if (nCount < 1) {
			return 0;
		}
		int nIdx = idx;
		for (int i = 0; i < nCount; i++) {
			int nOp = ((Integer) this.operations.get(i)).intValue();
			nOp &= 0xFFF;
			if (2 == nOp) {
				query.setParameter(nIdx, this.values.get(i));
				nIdx++;
			} else if (3 == nOp) {
				query.setParameter(nIdx, String.format("%%%s%%", new Object[] { this.values.get(i) }));
				nIdx++;
			} else if (1 == nOp) {
				QueryWhere w = (QueryWhere) this.values.get(i);
				nIdx += w.toParameters(nIdx, query);
			} else {
				query.setParameter(nIdx, this.values.get(i));
				nIdx++;
			}
		}
		return nIdx - idx;
	}
}
