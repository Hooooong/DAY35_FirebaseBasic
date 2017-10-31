Android Programing
----------------------------------------------------
### 2017.10.30 28일차

#### 예제
____________________________________________________

#### 공부정리
____________________________________________________

##### __Firebase__

- Firebase 란?

  > Firebase 는 웹과 모바일 개발에 필요한 기능을 제공하는 BaaS(Back-End as a Service)이다. 쉽게말해 Back-End 개발을 통해 서버를 따로 설계, 구현하지 않고 Front-End 개발에 집중할 수 있도록 도와주는 서비스이다. 기능으로는 실시간 데이터베이스, 간편한 사용자 인증, 클라우드 저장소, 호스팅, 앱 테스트와 수익 창출을 도와주는 등 다양한 기능을 제공해준다.

  - Bass(Back-End) :  온라인 서비스에서 사용자가 보는 프로그램(front-end)과 관리자가 보는 프로그램(back-end)으로 나눌 수 있다. 관리자 영역을 모듈화 하여 서비스로 제공하는 개념이 Baas이다. 예를 들어 온라인 분석이나 모니터링의 경우 관리자를 위한 서비스 등을 모듈화 하여 제공한다. 일반적으로 개발자가 모든 서비스를 만들기가 쉽지 않으므로 BaaS를 활용하여 개발 시간 단축 및 손쉽게 만들 수 있다.

- Firebase 제공 서비스

  - Authetication - 사용자들에 대한 인증 처리를 제공한다. Email&Password, Facebook, Twitter, GitHub, Google, Custom 등 다양한 사용자 인증에 대한 기능을 제공한다.

  - Realtime Database - Firebase는 실시간 데이터베이스와 백엔드 서비스를 제공한다. 이 서비스는 어플리케이션 개발자에게 하나의 API를 제공하는데 이 API는 어플리케이션 데이터가 고객 그리고 저장된 Firebase 클라우드와 자동 동기화되도록 해준다.

  - Storage - 개발자들이 쉽고 안전하게 파일들을 업로드하고 다운로드 할 수 있는 Firebase 저장소이다.

  - Firebase Cloud Messaging(FCM) - Firebase 는 다양한 기기로 푸시하는 메세징 서비스를 제공한다.

  - 그 외 기능 - Hosting, Functions, Test Lab, Crash Reporting, Performance, Remote Config, Dynamic Links

- Firebase 사용방법

  - Firebase 홈페이지에서 시작

      1. Android Project 생성

      2. [https://console.firebase.google.com](https://console.firebase.google.com) 에서 프로젝트 생성

      3. 2번에 나오는 내용 순서대로 작성

  - Android Studio 에서 시작

      1. Android Project 생성

      ![Firebase Start](https://github.com/Hooooong/DAY35_FirebaseBasic/blob/master/image/firebase.png)

      2. 위 이미지 순서대로 시작

##### __Firebase Realtime Database__

- Firebase Realtime Database 란?

  > Firebase는 실시간 데이터베이스와 백엔드 서비스를 제공한다. 이 서비스는 어플리케이션 개발자에게 하나의 API를 제공하는데 이 API는 어플리케이션 데이터가 고객 그리고 저장된 Firebase 클라우드와 자동 동기화되도록 해준다. 기본적으로 Firebase Realtime Database 는 NoSQL 로 제공이 된다.
  
- Firebase Realtime Database 사용 방법

  - 생성을 해주면 기본적으로 사용할 수 있지만, 규칙을 바꿔줘야 사용이 가능하다.

  - [https://firebase.google.com/docs/database/security/quickstart?authuser=0](https://firebase.google.com/docs/database/security/quickstart?authuser=0) 에서 Sample 규칙을 통해 규칙 수정

  - Gradle 설정 : [Firebase Database 설정](https://firebase.google.com/docs/database/android/start/?authuser=0)

  - 기본적인 선언

  ```java
  // 기본적으로 FirebaseDatabase 와 DatabaseReference 를 참조받아 사용한다.
  FirebaseDatabase database;
  DatabaseReference rootRef;
  DatabaseReference userRef;

  // 1. Firebase DataBase 와 Connection 을 한다.
  database = FirebaseDatabase.getInstance();

  // 2. Database 의 Node 연결
  // 아무런 Node 를 정의하지 않으면, root Node 를 참조한다.
  rootRef = database.getReference();

  // Root 하위에 있는 user Node 를 참조한다.
  userRef = database.getReference("user");
  ```

  - DataBase 값 변경 감지

  ```java
  // 값의 변경이 일어날 때 마다 캐치하는 메소드
  // DatabaseReference객체.addValueEventListener
  // 상시적으로 연결이 되있기 때문에, onResume(), onPause() 에서 Listener 의 작업을 해줘야 한다.
  userRef.addValueEventListener(new ValueEventListener() {
       @Override
       public void onDataChange(DataSnapshot dataSnapshot) {
         // 변경이 이뤄졌을 때 호출
       }

       @Override
       public void onCancelled(DatabaseError databaseError) {

       }
   });

   // 값의 변경사항을 한번만 캐치하는 메소드
   // DatabaseReference객체.addListenerForSingleValueEvent
   usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
       @Override
       public void onDataChange(DataSnapshot dataSnapshot) {
           // 변경이 이뤄졌을 때 호출
       }

       @Override
       public void onCancelled(DatabaseError databaseError) {

       }
   });
  ```

  - Data 넣기

  ![data Insert](https://github.com/Hooooong/DAY35_FirebaseBasic/blob/master/image/dataInsert.PNG)

  - Data 사용하기

  ![snapshot](https://github.com/Hooooong/DAY35_FirebaseBasic/blob/master/image/datasnapshat1.PNG)

  - 하위 Node 참조하기

  ![snapshot2](https://github.com/Hooooong/DAY35_FirebaseBasic/blob/master/image/datasnapshat2.PNG)

  - Data 수정하기

  ```java
  // 1. HashMap 에 Key 와 Value 를 넣는다.
  HashMap<String, Object> update = new HashMap<>();
  update.put("username", "이능기");
  // 2. updateChildren 메소드를 통해
  // 'User/id에 맞는' Node 를 참조하여 값을 수정한다.
  userRef.child(id).updateChildren(update);

  // 수정 되었는지 확인하는 Listener 를 달아줄 수 있다.
  userRef.child(id).updateChildren(update, new DatabaseReference.CompletionListener() {
      @Override
      public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

      }
  });
  ```

  - Data 삭제하기

  ```java
  // 'User/id에 맞는' Node 를 참조하여 값을 수정한다.
  userRef.child(id).removeValue();

  // 삭제되었는지 확인하는 Listener 를 달아줄 수 있다.
  userRef.child(id).removeValue(new DatabaseReference.CompletionListener() {
      @Override
      public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

      }
  });
  ```
