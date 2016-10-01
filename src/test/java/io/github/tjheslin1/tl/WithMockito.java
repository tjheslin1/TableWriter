package io.github.tjheslin1.tl;

import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.mockito.verification.VerificationMode;

public interface WithMockito {

    default <T> T mock(Class<T> classToMock) {
        return Mockito.mock(classToMock);
    }

    default <T> OngoingStubbing<T> when(T methodCall) {
        return Mockito.when(methodCall);
    }

    default <T> T verify(T mock) {
        return Mockito.verify(mock);
    }

    default <T> T verify(T mock, VerificationMode mode) {
        return Mockito.verify(mock, mode);
    }

    default VerificationMode times(int numOfInvocations) {
        return Mockito.times(numOfInvocations);
    }

    default <T> T any() {
        return Mockito.any();
    }

    default <T> T eq(T value) {
        return Mockito.eq(value);
    }

    default <T> T spy(T object) {
        return Mockito.spy(object);
    }
}
