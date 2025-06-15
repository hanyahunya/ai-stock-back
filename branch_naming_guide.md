# ブランチ命名規則 (Branch Naming Guide)

### - 基本ルール

```
ドメイン/prefix-機能名
```

例:

- `users/feature-join`
- `stocks/bugfix-rounding`
- `util/refactor-date-utils`

### - ドメイン (Domain)

ブランチが属する機能領域

- `users` : ユーザー関連
- `stocks` : 株式・金融
- `util` : 共通ユーティリティコード
- `common` : 全体共通モジュール

### - prefix 種類

| prefix     | 意味       | 例                              |
| ---------- | -------- | ------------------------------ |
| `feature`  | 新しい機能開発  | `users/feature-join`           |
| `bugfix`   | バグ修正     | `stocks/bugfix-price-rounding` |
| `refactor` | リファクタリング | `util/refactor-date-utils`     |
| `hotfix`   | 緊急修正     | `users/hotfix-login-crash`     |
| `test`     | テストコード   | `members/test-service-layer`   |
| `chore`    | 雑務（ビルド等） | `common/chore-gradle-update`   |

---


## 브랜치 네이밍 가이드 (Branch Naming Guide)

### - 기본 규칙

```
도메인/prefix-기능명
```

예시:

- `users/feature-join`
- `stocks/bugfix-rounding`
- `util/refactor-date-utils`

### - 도메인 (Domain)

브랜치가 속한 기능 영역

- `users` : 사용자 관련 기능
- `stocks` : 주식/금융 관련
- `util` : 공통 유틸리티 코드
- `common` : 전역 공통 모듈

### - prefix 종류

| prefix     | 의미        | 예시                             |
| ---------- | --------- | ------------------------------ |
| `feature`  | 새로운 기능 개발 | `users/feature-join`           |
| `bugfix`   | 버그 수정     | `stocks/bugfix-price-rounding` |
| `refactor` | 리팩터링      | `util/refactor-date-utils`     |
| `hotfix`   | 긴급 수정     | `users/hotfix-login-crash`     |
| `test`     | 테스트 코드    | `members/test-service-layer`   |
| `chore`    | 기타 잡일     | `common/chore-gradle-update`   |

---
