# Demo redis+jwt+microservice
### 1. service authentication (jwt)
![image](https://github.com/phamtronghieu2002/KienTrucPhanMem_Springboot_Redis_JWT/assets/109363404/31449ab2-13ba-495a-ad8d-6eed5d36a3c8)

 1. user đăng kí tài khoản
 ![image](https://github.com/phamtronghieu2002/KienTrucPhanMem_Springboot_Redis_JWT/assets/109363404/8f0f2bcd-b4d0-44db-ba65-f61c2da4bf7e)
 2. user đăng nhập để lấy token
 ![image](https://github.com/phamtronghieu2002/KienTrucPhanMem_Springboot_Redis_JWT/assets/109363404/30d85a17-c84b-4b7e-a6f6-3493b0c40127)

### 2.Service chứa User và Car  (redis)

  1.Lưu user vào db và redis:
  ![image](https://github.com/phamtronghieu2002/KienTrucPhanMem_Springboot_Redis_JWT/assets/109363404/eeedf14d-c747-4736-b7c4-404bf14f3d0e)
  ![image](https://github.com/phamtronghieu2002/KienTrucPhanMem_Springboot_Redis_JWT/assets/109363404/4c2f68f3-045c-4491-8df5-f0993ceb389a)
  
  2.gọi api get user 
    +> khi get user call API để get car và không lưu vào redis
      ![image](https://github.com/phamtronghieu2002/KienTrucPhanMem_Springboot_Redis_JWT/assets/109363404/125309bc-2e4c-4ff1-8790-f80a6f34f73c)
       ![image](https://github.com/phamtronghieu2002/KienTrucPhanMem_Springboot_Redis_JWT/assets/109363404/8f86ae8c-d9b1-4d13-a582-c92bf39f0f82)
    +> khi get user call API để get car và lưu và sử dụng redis
       ![image](https://github.com/phamtronghieu2002/KienTrucPhanMem_Springboot_Redis_JWT/assets/109363404/3b61abfc-5f89-4b82-ae7d-50ac2214ee20)
       ![image](https://github.com/phamtronghieu2002/KienTrucPhanMem_Springboot_Redis_JWT/assets/109363404/d8ff92bc-9982-4011-afde-b2dcb5ab17c9)


     


	
