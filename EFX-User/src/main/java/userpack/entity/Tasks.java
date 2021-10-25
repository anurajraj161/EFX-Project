package userpack.entity;


public class Tasks {


		private Long T_id;
		
		private String T_name;
		
		private Long U_id;

		public Tasks(Long t_id, String t_name, Long u_id) {
			super();
			T_id = t_id;
			T_name = t_name;
			U_id = u_id;
		}

		public long getT_id() {
			return T_id;
		}

		public void setT_id(Long t_id) {
			T_id = t_id;
		}

		public String getT_name() {
			return T_name;
		}

		public void setT_name(String t_name) {
			T_name = t_name;
		}

		public Long getU_id() {
			return U_id;
		}

		public void setU_id(Long u_id) {
			U_id = u_id;
		}

		public Tasks() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
		

}
