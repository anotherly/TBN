package kr.co.wizbrain.tbn.comm;

import java.util.*;

public class BaseVO {

	// DataTables 표준 파라미터 (전 화면 공통)
	private Integer draw;
	private Integer start;
	private Integer length;

	// ★ List로 선언해야 columns[0] / order[0] 바인딩 가능
	private List<Order> order = new ArrayList<>();
	private List<Column> columns = new ArrayList<>();

	//기타 검색조건들
    private String sDate, eDate ,searchType, searchValue;
    
	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public BaseVO() {
	} // 기본 생성자 필수

	// ===== getters / setters =====
	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	// ========= 내부 타입 (전 화면 공통) =========
	public static class Order {
		private Integer column; // index
		private String dir; // "asc"/"desc"

		public Order() {
		}

		public Integer getColumn() {
			return column;
		}

		public void setColumn(Integer column) {
			this.column = column;
		}

		public String getDir() {
			return dir;
		}

		public void setDir(String dir) {
			this.dir = dir;
		}
	}

	public static class Column {
		private String data; // JS columns[i].data
		private String name; // JS columns[i].name ← ORDER BY 매핑에 활용
		private Search search; // 필요시 사용

		public Column() {
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Search getSearch() {
			return search;
		}

		public void setSearch(Search search) {
			this.search = search;
		}
	}

	public static class Search {
		private String value;
		private Boolean regex;

		public Search() {
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Boolean getRegex() {
			return regex;
		}

		public void setRegex(Boolean regex) {
			this.regex = regex;
		}
	}

	// ========= 공통 헬퍼 =========
	/**
	 * DataTables 정렬 파라미터로부터 안전한 ORDER BY를 생성. columns[idx].name 값을 화이트리스트로 검증하여 반환.
	 */
	public String buildOrderBy(Set<String> whitelist, String defaultOrderBy) {
		if (order == null || order.isEmpty())
			return defaultOrderBy;
		Order od = order.get(0);
		if (od == null || od.getColumn() == null)
			return defaultOrderBy;

		int idx = od.getColumn();
		String dir = "desc".equalsIgnoreCase(od.getDir()) ? "DESC" : "ASC";

		if (columns != null && idx >= 0 && idx < columns.size()) {
			String name = columns.get(idx) != null ? columns.get(idx).getName() : null;
			if (name != null) {
				String col = name.trim().toUpperCase(Locale.ROOT);
				if (whitelist.contains(col)) {
					// 필요 시 특정 컬럼 NULLS LAST 같은 후처리
					return col + " " + dir + ("REG_DATE".equals(col) ? " NULLS LAST" : "");
				}
			}
		}
		return defaultOrderBy;
	}
}