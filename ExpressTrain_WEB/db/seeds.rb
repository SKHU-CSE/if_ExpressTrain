# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)

## 카테고리
Category.create(category_name: "한식")
Category.create(category_name: "중식")
Category.create(category_name: "분식")
Category.create(category_name: "제과점")
Category.create(category_name: "패스트푸드")
Category.create(category_name: "부식")

## 관리자
Member.create(email:'admin', password: '123123', name: '관리자', mail: 'admin@email', authorization: 1)

## apply
Apply.create(store_name: "소셜캠퍼스 온", category_id: 6, address: "서울특별시 성동구 광나루로 286 아인빌딩 8~9층 소셜캠퍼스 온",
            phone: "02-123-4567", photo: "assets/SocialCampus.jpg", check: 0)

## 가맹점
Store.create(store_name: "파리바게트(녹번역점)", category_id: 4, address: "서울특별시 은평구 은평로 237",
            phone: "070-7556-8204", latitude: "37.599039", longitude: "126.919684", photo: "assets/photo5.jpg")
Store.create(store_name: "즐거운 반딧불이", category_id: 4, address: "서울특별시 은평구 은평로21길 32",
            phone: "070-8223-7529", latitude: "37.602565", longitude: "126.930927", photo: "assets/photo1.jpg")
Store.create(store_name: "청다래", category_id: 1, address: "서울특별시 은평구 진흥로 82",
            phone: "010-5275-6470", latitude: "37.603998", longitude: "126.922073", photo: "assets/photo2.jpg")            
Store.create(store_name: "북경반점", category_id: 2, address: "서울특별시 은평구 은평로 205-7",
            phone: "02-352-7723", latitude: "37.601541", longitude: "126.931273", photo: "assets/photo10.jpg")
Store.create(store_name: "소풍", category_id: 1, address: "서울특별시 은평구 진흥로 68-10",
            phone: "02-3273-1237", latitude: "37.602982", longitude: "126.922078", photo: "assets/photo8.jpg") 
Store.create(store_name: "뚜레쥬르(불광현대)", category_id: 4, address: "서울특별시 은평구 불광로 60",
            phone: "02-352-0555", latitude: "37.612208", longitude: "126.93213", photo: "assets/photo7.jpg")
Store.create(store_name: "수연떡방", category_id: 6, address: "서울특별시 은평구 불광로 132",
            phone: "02-354-7333", latitude: "37.618557", longitude: "126.93309", photo: "assets/photo11.jpg")
Store.create(store_name: "김밥천국(연신내점)", category_id: 1, address: "서울특별시 은평구 연서로 270-1",
            phone: "02-382-7006", latitude: "37.620367", longitude: "126.924725", photo: "assets/photo9.jpg")  
Store.create(store_name: "꼬치닭", category_id: 3, address: "서울특별시 은평구 불광로 125",
            phone: "010-6223-7830", latitude: "37.617869", longitude: "126.932999", photo: "assets/photo8.jpg")  
Store.create(store_name: "파리바게트(불광점)", category_id: 4, address: "서울특별시 은평구 연서로 286",
            phone: "02-382-9033", latitude: "37.621101", longitude: "126.926152", photo: "assets/photo5.jpg")
Store.create(store_name: "이삭토스트", category_id: 3, address: "서울특별시 은평구 연서로 294",
            phone: "02-3157-8816", latitude: "37.619088", longitude: "126.923502", photo: "assets/photo6.jpg")
Store.create(store_name: "공화춘", category_id: 2, address: "서울특별시 은평구 연서로 306",
            phone: "02-354-9955", latitude: "37.62264", longitude: "126.927522", photo: "assets/photo4.png") 
Store.create(store_name: "락궁", category_id: 2, address: "서울특별시 은평구 연서로41길 5",
            phone: "02-355-2233", latitude: "37.625618", longitude: "126.92815", photo: "assets/photo8.jpg") 
Store.create(store_name: "아비앙또", category_id: 4, address: "서울특별시 은평구 연서로 341",
            phone: "02-359-3217", latitude: "37.625604", longitude: "126.928681", photo: "assets/photo8.jpg")