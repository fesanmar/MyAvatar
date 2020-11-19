package com.felipesantacruz.myavatar.avatar;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class AvatarBuilderPublisherTest {
    private Avatar.Builder builder = new Avatar.Builder();
    private AvatarBuilderClient CLIENT_A = mock(AvatarBuilderClient.class, "clientA");
    private AvatarBuilderClient CLIENT_B = mock(AvatarBuilderClient.class, "clientB");

    @Test
    public void subscribersMustBeNotifiedWhenAvatarIsBuild() {
        builder.addSubscriber(CLIENT_A);
        Avatar avatar = builder.build();
        verify(CLIENT_A).receive(avatar);
    }

    @Test
    public void multipleSubscriberMustBeNotifiedWhenAvatarIsBuild() {
        builder.addSubscriber(CLIENT_A);
        builder.addSubscriber(CLIENT_B);
        Avatar avatar = builder.build();
        verify(CLIENT_A).receive(avatar);
        verify(CLIENT_B).receive(avatar);
    }

    @Test
    public void oneSubscriberSubscribedTwiceMustBeNotifiedOnceWhenAvatarIsBuild() {
        builder.addSubscriber(CLIENT_A);
        builder.addSubscriber(CLIENT_A);
        Avatar avatar = builder.build();
        verify(CLIENT_A).receive(avatar);
    }

    @Test
    public void unsubscribedClientMustNotBeNotifiedWhenAvatarIsBuild() {
        builder.addSubscriber(CLIENT_A);
        builder.unsubscribe(CLIENT_A);
        Avatar avatar = builder.build();
        verify(CLIENT_A, never()).receive(avatar);
    }
}
