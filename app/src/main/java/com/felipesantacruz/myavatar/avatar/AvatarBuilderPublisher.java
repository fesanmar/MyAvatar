package com.felipesantacruz.myavatar.avatar;

public interface AvatarBuilderPublisher {
    void publish();

    void addSubscriber(AvatarBuilderClient client);

    void unsubscribe(AvatarBuilderClient client);
}
