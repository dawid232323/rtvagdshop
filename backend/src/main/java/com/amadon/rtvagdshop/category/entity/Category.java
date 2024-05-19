package com.amadon.rtvagdshop.category.entity;

import com.amadon.rtvagdshop.shared.entity.DefaultEntity;

import javax.annotation.Nullable;
import java.util.Objects;

public interface Category extends DefaultEntity
{
    String getCode();

    String getDisplayName();

    @Nullable
    Category getParent();

    default boolean hasParent() {
        return !Objects.isNull( getParent() );
    }
}
