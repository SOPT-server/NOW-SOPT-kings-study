## 검색 API

### 연관된 테이블

- 리포트(통계)
- 스크랩 여부 (Scrap)
- 부동산 가격 정보(LimjangPrice)
- 이미지(Image)

### `containsIgnoreCase`

- 이 메소드 쓰면 자동으로 Lower로 바꾸어주고, 입력한 값 양 옆에 %를 붙여준다.
- 소문자로 변환할 필요없으면 그냥 `contains` 써주어도 된다.

## `keywordOf`

- 인자로 전해진 removeBlank() 메소드들 사이에 하나라도 참이라면(nickname, address, addressDetail 컬럼 중 하나라도 키워드가 포함된다면) 해당 데이터 반환

## `removeBlank`

- Expressions.stringTemplate으로 컬럼 공백제거

```java
 @Override
  public List<Limjang> searchLimjangs(Member member, String keyword) {
    String rKeyword = keyword.replaceAll(" ", "");

    return queryFactory
        .selectFrom(limjang)
        .leftJoin(limjang.report, report).fetchJoin()
        .leftJoin(limjang.scrap, scrap).fetchJoin()
        .leftJoin(limjang.priceId, limjangPrice).fetchJoin()
        .leftJoin(limjang.imageList, image).fetchJoin()
        .where(limjang.memberId.eq(member),
            keywordOf(
                removeBlank(limjang.nickname).containsIgnoreCase(rKeyword),
                removeBlank(limjang.address).containsIgnoreCase(rKeyword),
                removeBlank(limjang.addressDetail).containsIgnoreCase(rKeyword)
            ))
        .fetch();
  }

  private BooleanExpression keywordOf(BooleanExpression... conditions) {
    BooleanExpression result = null;
    for (BooleanExpression condition : conditions) {
      result = result == null ? condition : result.or(condition);
    }
    return result;
  }

  private StringExpression removeBlank(StringExpression origin) {
    return Expressions.stringTemplate("function('replace', {0}, ' ', '')", origin);
  }
```

### 쿼리

```sql
select
    l1_0.limjang_id,
    l1_0.address,
    l1_0.address_detail,
    l1_0.created_at,
    l1_0.deleted,
    il1_0.limjang_id,
    il1_0.image_id,
    il1_0.created_at,
    il1_0.image_url,
    il1_0.updated_at,
    l1_0.member_id,
    l1_0.memo,
    l1_0.nickname,
    pi1_0.price_id,
    pi1_0.created_at,
    pi1_0.deposit_price,
    pi1_0.market_price,
    pi1_0.monthly_rent,
    pi1_0.pull_rent,
    pi1_0.selling_price,
    pi1_0.updated_at,
    l1_0.price_type,
    l1_0.property_type,
    l1_0.purpose,
    l1_0.record_count,
    r1_0.report_id,
    r1_0.created_at,
    r1_0.indoor_keyword,
    r1_0.indoor_rate,
    r1_0.location_conditions_keyword,
    r1_0.location_conditions_rate,
    r1_0.public_space_keyword,
    r1_0.public_space_rate,
    r1_0.total_rate,
    r1_0.updated_at,
    s1_0.scrap_id,
    s1_0.created_at,
    s1_0.updated_at,
    l1_0.updated_at
from
    limjang l1_0
left join
    report r1_0
        on l1_0.limjang_id=r1_0.limjang_id
left join
    scrap s1_0
        on l1_0.limjang_id=s1_0.limjang_id
left join
    limjang_price pi1_0
        on pi1_0.price_id=l1_0.price_id
left join
    image il1_0
        on l1_0.limjang_id=il1_0.limjang_id
where
    (
        l1_0.deleted = false
    )
    and l1_0.member_id=?
    and (
        lower(replace(l1_0.nickname, ' ', '')) like ? escape '!'
        or lower(replace(l1_0.address, ' ', '')) like ? escape '!'
        or lower(replace(l1_0.address_detail, ' ', '')) like ? escape '!'
    )

```
```

## 메인화면 API

연관된 테이블 

- 리포트(통계)
- 스크랩 여부 (Scrap)
- 부동산 가격 정보(LimjangPrice)
- 이미지(Image)

```java
  @Override
  public List<Limjang> findMainScreenContentsLimjang(Member member) {

    return queryFactory
        .selectFrom(limjang)
        .leftJoin(limjang.report, report).fetchJoin()
        .leftJoin(limjang.scrap, scrap).fetchJoin()
        .leftJoin(limjang.priceId, limjangPrice).fetchJoin()
        .leftJoin(limjang.imageList, image).fetchJoin()
        .where(limjang.memberId.eq(member))
        .orderBy(limjang.updatedAt.desc())
        .limit(5)
        .fetch();
  }
```

### 쿼리

```sql
    select
        l1_0.limjang_id,
        l1_0.address,
        l1_0.address_detail,
        l1_0.created_at,
        l1_0.deleted,
        il1_0.limjang_id,
        il1_0.image_id,
        il1_0.created_at,
        il1_0.image_url,
        il1_0.updated_at,
        l1_0.member_id,
        l1_0.memo,
        l1_0.nickname,
        pi1_0.price_id,
        pi1_0.created_at,
        pi1_0.deposit_price,
        pi1_0.market_price,
        pi1_0.monthly_rent,
        pi1_0.pull_rent,
        pi1_0.selling_price,
        pi1_0.updated_at,
        l1_0.price_type,
        l1_0.property_type,
        l1_0.purpose,
        l1_0.record_count,
        r1_0.report_id,
        r1_0.created_at,
        r1_0.indoor_keyword,
        r1_0.indoor_rate,
        r1_0.location_conditions_keyword,
        r1_0.location_conditions_rate,
        r1_0.public_space_keyword,
        r1_0.public_space_rate,
        r1_0.total_rate,
        r1_0.updated_at,
        s1_0.scrap_id,
        s1_0.created_at,
        s1_0.updated_at,
        l1_0.updated_at 
    from
        limjang l1_0 
    left join
        report r1_0 
            on l1_0.limjang_id=r1_0.limjang_id 
    left join
        scrap s1_0 
            on l1_0.limjang_id=s1_0.limjang_id 
    left join
        limjang_price pi1_0 
            on pi1_0.price_id=l1_0.price_id 
    left join
        image il1_0 
            on l1_0.limjang_id=il1_0.limjang_id 
    where
        (
            l1_0.deleted = false
        ) 
        and l1_0.member_id=? 
    order by
        l1_0.updated_at desc
```

## 고민한거

- 쿼리 나갈 때 대상 테이블과 조인된 모든 테이블의 컬럼이 다 조회되는데 이게 맞는지 → 보통 역할이 리포지토리에서 엔티티 조회하고, 서비스 계층에서 DTO로 가공해주니까
- 김영한님 최적화 내용 보니까 엔티티로 모두 조회하나 DTO로 일부 컬럼만 조회하나 사실 성능상 별로 차이는 없다함
- **[수십억건에서 QUERYDSL 사용하기](https://youtu.be/zMAX7g6rO_Y?si=AP42XASlT3aJuT8n)** 영상에서 보니까 대용량 데이터 처리할 때는 DTO로 조회하는 것을 권장한다고 합니다 (영상 내용 유익해서 한번 보면 좋을 듯)
