package com.gitittech.paygo.user.mappers;

import com.gitittech.paygo.entities.JpaUser;
import com.gitittech.paygo.entities.UserWithBalanceView;
import com.gitittech.paygo.user.dtos.User;
import com.gitittech.paygo.user.dtos.UserBriefDetails;
import com.gitittech.paygo.user.dtos.UserWithToken;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper( IUserMapper.class );

    JpaUser toJpaUser(User user);

    User toUser(UserWithBalanceView user);

    User toUser(JpaUser user);

    UserWithToken toUserWithToken(UserWithBalanceView user);

    UserBriefDetails toUserBriefDetails(JpaUser jpaUser);
}
