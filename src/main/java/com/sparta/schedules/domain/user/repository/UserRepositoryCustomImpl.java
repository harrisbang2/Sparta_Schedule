package com.sparta.schedules.domain.user.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.schedules.domain.user.entity.QUser;
import com.sparta.schedules.domain.user.entity.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final QUser user = QUser.user;

    @Override
    public Optional<User> findByUsername(String username) {
        BooleanExpression predicate = user.username.eq(username);

        return Optional.ofNullable(jpaQueryFactory
            .select(Projections.fields(User.class, user.id,user.username,user.password,user.role))
            .from(user)
            .where(predicate)
            .fetchOne());
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username,String password) {
        BooleanExpression predicate = user.username.eq(username).and(user.password.eq(password));

        return Optional.ofNullable(jpaQueryFactory
            .select(Projections.fields(User.class, user.id,user.username,user.password))
            .from(user)
            .where(predicate)
            .fetchOne());
    }
}
