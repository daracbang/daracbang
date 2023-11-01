package a503.daracbang.domain.member.util;

public class MemberContextHolder {
	public static final ThreadLocal<Long> memberIdHolder = ThreadLocal.withInitial(() -> null);
}
