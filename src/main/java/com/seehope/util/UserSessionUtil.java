package com.seehope.util;

import com.seehope.entity.Seehope_student;
import com.seehope.entity.Seehope_teacher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserSessionUtil {
	private static String SESSION_KEY_ADMIN_TEACHER = "admin_teacher";
	private static String SESSION_KEY_EDU_STUDENT = "edu_student";
	private static String SESSION_KEY_ADMIN_MENU = "admin_menu";

	public static void setAdminTeacher(Seehope_teacher teacher, HttpServletRequest request) {
		request.getSession().setAttribute(SESSION_KEY_ADMIN_TEACHER, teacher);
	}

	public static Seehope_teacher getAdminTeacher(HttpServletRequest request) {
		return (Seehope_teacher) request.getSession().getAttribute(SESSION_KEY_ADMIN_TEACHER);
	}

	public static void removeAdminTeacher(HttpServletRequest request) {
		request.getSession().removeAttribute(SESSION_KEY_ADMIN_TEACHER);
	}

	public static void setAdminMenu(HttpServletRequest request, String menujson) {
		request.getSession().setAttribute(SESSION_KEY_ADMIN_MENU, menujson);
	}

	public static String getAdminMenu(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(SESSION_KEY_ADMIN_MENU);
	}

	public static void removeAdminMenu(HttpServletRequest request) {
		request.getSession().removeAttribute(SESSION_KEY_ADMIN_MENU);
	}

	public static void setEduStudent(Seehope_student student, HttpServletRequest request) {
		request.getSession().setAttribute(SESSION_KEY_EDU_STUDENT, student);
	}

	public static Seehope_student getEduStudent(HttpServletRequest request) {
		return (Seehope_student) request.getSession().getAttribute(SESSION_KEY_EDU_STUDENT);
	}

	public static void removeEduStudent(HttpServletRequest request) {
		request.getSession().removeAttribute(SESSION_KEY_EDU_STUDENT);
	}
}
