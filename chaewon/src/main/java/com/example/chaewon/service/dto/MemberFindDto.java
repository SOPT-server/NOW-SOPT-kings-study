package com.example.chaewon.service.dto;

import com.example.chaewon.domain.Member;

public record MemberFindDto(

        String name,
        String email,
        String picture
) {
    public static MemberFindDto of(Member member) {
        return new MemberFindDto(
                member.getName(),
                member.getEmail(),
                member.getPicture());
    }

}
