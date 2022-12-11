## 장소검색
#### 개발환경
- IDE : inteillJ
- JAVA : java8
- 빌드 : GRADLE
- db : H2 inmemory

## 요구사항 
#### API 검색 결과
- 장소 명의 공백 및 태그를 제거하여 각 api 연동사의 우선순위를 기준으로 장소명을 정렬하여 결과를 추출했습니다.

#### 새로운 API 제공자
- API 제공자가 추가되어라도 연동사에 API 스택과 공통 VendorPlaceSearchResponse 모델만 동일하게 구성하면 되도록 구성했습니다.
연동사 API에 경우 place bean holder에 등록 되며, 이후 검색 결과에선 연동사와 무관하게 동작하게 구성되었습니다.
  
#### 동시성 이슈
- 키워드 별 조회수의 경우 동시성에 이슈가 있어 비관적 락을 사용했습니다.
- 하지만, 트래픽이 몰릴 경우 문제가 발생할 수 있어 큐를 사용해서 재처리를 하는 방향도 고민을 해와야 할 것 같습니다.

#### 장애 발생
- API 제공자의 장애시 응답이 늦은 경우를 대비해 비동기로 호출하여. 처리되도록 했습니다. 
- 문제가 발생한 부분에 대해선 호출자에게 전달되도록 했습니다. 


#### 지속적 유지 보수 및 확장에 용이한 아키텍처에 대한 설계
- 새로운 제공자나 에러처리를 진행할 수 있는 부분들을 설계 해놓아 추가 적인 작업으로 손쉽게 확장 가능합니다.

#### 실행
```
# ./gradlew build  
# java -jar ./build/libs/place-1.0-SNAPSHOT.jar      
```

#### 장소검색
```
# curl --location --request GET 'http://localhost:8080/v1/place/search?keyword=서울'
# curl --location --request GET 'http://localhost:8080/v1/place/search?keyword=잠실'    
```

#### 키워드 검색
```
# curl --location --request GET 'http://localhost:8080/v1/place/keyword/ranking'  
```

#### 포멧 - 장소
```
[
    {
        "name": {장소명}
    },
    ...
]
```

#### 포멧 - 키워드
```
[
    {
        "name": {검색어},
        "count": {횟수}
    },
    ...
]
```