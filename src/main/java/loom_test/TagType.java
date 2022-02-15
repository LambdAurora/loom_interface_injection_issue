package loom_test;

public enum TagType {
	NORMAL,
	CLIENT_FALLBACK,
	CLIENT_ONLY;

	public boolean hasSync() {
		return this != CLIENT_ONLY;
	}
}
