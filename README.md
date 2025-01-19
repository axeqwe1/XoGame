# วิธีติดตั้งและตั้งค่าโปรเจกต์ Tic-Tac-Toe

## คำอธิบายโปรเจกต์
นี่คือแอปพลิเคชันเกม Tic-Tac-Toe ที่พัฒนาด้วย Jetpack Compose สำหรับ Android โดยมีฟีเจอร์ต่างๆ เช่น การตั้งค่าชื่อผู้เล่น ขนาดกระดาน ประวัติการเล่น และการแสดงผลผู้ชนะ

---

## การตั้งค่าระบบเบื้องต้น

### ความต้องการของระบบ
- **Android Studio:** version 2024 ขึ้นไป
- **JDK:** 11 หรือสูงกว่า
- **Gradle:** 7.0 หรือใหม่กว่า
- **ระบบปฏิบัติการ:** Windows, macOS หรือ Linux

### Dependency ที่ใช้ในโปรเจค
   - **Android & Jetpack Compose**
   - **Core libraries**
   - **Compose UI libraries**
   - **Room (สำหรับจัดการฐานข้อมูลในเครื่อง)**
   - **Hilt (Dependency Injection)**
   - **Navigation (การจัดการการนำทางสู่หน้าจออื่น)**

### ขั้นตอนการตั้งค่า

1. **ติดตั้ง Android Studio**
   - ดาวน์โหลดและติดตั้ง Android Studio จาก [Android Developer Website](https://developer.android.com/studio).

2. **โคลนโปรเจกต์**
   ```bash
   git clone https://github.com/axeqwe1/XoGame.git
   cd ไปที่ Folder โปรเจค
   ```

3. **เปิดโปรเจกต์ใน Android Studio**
   - เปิด Android Studio แล้วเลือก "Open Project".
   - ไปยังโฟลเดอร์ที่โคลนไว้ แล้วเลือกไฟล์ `build.gradle`

4. **ซิงค์ Gradle**
   - เมื่อเปิดโปรเจกต์แล้ว Android Studio จะร้องขอให้คุณซิงค์ Gradle คลิก "Sync Now" เพื่อเริ่มกระบวนการ.

5. **ตั้งค่า Emulator หรือ Device จริง**
   - ตั้งค่า Android Emulator ผ่าน AVD Manager ใน Android Studio.
   - หรือเชื่อมต่ออุปกรณ์จริงและเปิดใช้งาน USB Debugging.
   - แนะนำ Emulator API 33+ 

---

## การติดตั้งและรันโปรเจกต์

1. **ติดตั้ง Dependencies**
   - ตรวจสอบให้แน่ใจว่าได้ติดตั้ง Hilt และ Library ที่เกี่ยวข้องทั้งหมด โดย Gradle จะจัดการติดตั้งให้อัตโนมัติหลังการซิงค์.

2. **คอมไพล์และรันโปรเจกต์**
   - คลิกที่ปุ่ม "Run" หรือใช้คีย์ลัด `Shift + F10`.
   - เลือกอุปกรณ์ Emulator หรือ Device จริงที่ต้องการรัน.

3. **เริ่มเล่นเกม**
   - เมื่อแอปเปิดขึ้น คุณสามารถตั้งค่าผู้เล่นและขนาดกระดาน จากนั้นเริ่มเกมได้ทันที.
---

## อธิบาย Algorithm
- Algorithm นี้จะอยู่ใน Function checkWinner และ checkDraw ที่ screen/GameScreen.kt
- อย่างแรก คือเราต้องเก็บข้อมูลขนาดไซต์ของตัว Board เกมก่อนโดยจะสร้างตัวแปรให้เก็บเป็น List โดยข้างใน List จะเก็บเป็น Array ของ String
- var board = remember {mutableStateListOf(*Array(boardSize) { MutableList(boardSize) { "" } })}
- ให้เห็นภาพตัวแปร สมมุต BoardSize = 3 โดย mutableStateListOf(*Array(boardSize) จะสร้าง Array ขึ้นมา 3 ตัว MutableList(boardSize) { "" } จะสร้าง text เปล่า("") มา 3 ตัว
- board[
  - ["","",""]
  - ["","",""]
  - ["","",""]
  - ] เวลา Access ข้อมูลจะ Access ได้เหมือน Array 2 มิติ board[][]
- เริ่ม Algorithm สำหรับตรวจสอบหาผู้ชนะ
  - โดยจะทำการสร้าง function checkWinner โดยจะมา parameter เป็นตัว board ที่เก็บข้อมูลเป็น List หรือก็คือตาราง XO ทั้งหมด และ BoardSize ขนาดของ Board โดย function จะ Return เป็น Boolean
- โดยจะไล่ตรวจตามแถว Row ก่อน(แนวนอน)
  - ![image](https://github.com/user-attachments/assets/ffa54a7b-01cb-4421-9f43-8fdec0d15394)
  - โดยจะขอแทน x คือRow(แนวนอน) cell คือข้อมูลใน board ของแถว แนวนอน
  - ประกาศตัวแปร rowWinner โดยกำหนด Default คือ True 
  - วนลูป 2 ชั้น
  - โดยลูปชั้นแรกจะเป็นการวนลูปแถว Rowก่อนโดยจะเริ่มจาก row ที่1 (แต่ index ของ array คือ 0)
  - โดยลูปชั้นที่สองจะเป็นการวนลูปใน cell ลูปจะเริ่มจาก cell ที่ 2 เพื่อป้องกัน indexOutBoundเวลาตรวจสอบว่าเซลปัจจุบันกับเซลก่อนหน้านี้มีค่าเหมือนกันไหม ถ้าไม่เหมือนกันให้กำหนด rowWinner = False และ Break ออกจากลูปที่ 2 เพื่อไปวนลูปที่ 1
  - กรณี rowWinner ยังเป็น True และ cell แรกของแถว x ไม่ว่าง(เพื่อดูว่าแถว row ไหนชนะ)ให้ return true ไปที่ function checkWinner 
  - โดยหลักการ for loop สองชั้น จะทำการวนลูปชั้นที่ 2 ให้ก่อนเสร็จเสมอแล้วถึงไปวนลูปชั้นที่ 1

- ต่อไปเป็นการตรวจสอบแถว Column (แนวตั้ง)
  - ![image](https://github.com/user-attachments/assets/d1b0dd27-8690-4daf-834d-9bf12e757fe2)
  - โดยจะขอแทน y คือColumn(แนวตั้ง) cell คือข้อมูลใน board ของแถว แนวตั้ง
  - ประกาศตัวแปร colWinner โดยกำหนด Default คือ True
  - วนลูป 2 ชั้น
  - โดยลูปชั้นแรกจะเป็นการวนลูป Column ก่อนโดยจากเริ่มจาก Column ที่ 1 (แต่ index ของ array คือ 0)
  - โดยลูปชั้นที่สองจะเป็นการวนลูปใน cell ลูปจะเริ่มจาก cell ที่ 2 เพื่อป้องกัน indexOutBoundเวลาตรวจสอบว่าเซลปัจจุบันกับเซลก่อนหน้านี้มีค่าเหมือนกันไหม ถ้าไม่เหมือนกันให้กำหนด colWinner = False และ Break ออกจากลูปที่ 2 เพื่อไปวนลูปที่ 1
  - กรณี colWinner ยังเป็น True และ cell แรกของแถว y ไม่ว่าง(เพื่อดูว่าแถว column ไหนชนะ)ให้ return true ไปที่ function checkWinner
 
- ต่อไปเป็นการตรวจสอบแถว Main Diagonal (เส้นทแยงมุมขวา)
  - ![image](https://github.com/user-attachments/assets/b547b5a6-3d48-463c-a79d-d1bbb6a8ea5d)
  - โดยจะขอแทน i เป็นตำแหน่งของ cell ใน board
  - ประกาศตัวแปร mainDiagWinner โดยกำหนด Default คือ True
  - วนลูป 1 ชั้น
  - โดยลูปชั้นแรกจะเป็นการวนลูปตำแหน่ง Column และ Row โดยจะเริ่มจากตำแหน่งที่ 2 ของ Column และ Row (index ของ array คือ 1) เพื่อที่จะสามารถตรวจสอบป้องกันการ indexOutBound และตรวจสอบว่าเซลปัจจุบันกับเซลก่อนหน้านี้มีค่าเหมือนกันไหม ถ้าไม่เหมือนกันให้กำหนด mainDiagWinner = False และ Break ออกจากลูป
  - กรณี mainDiagWinner ยังเป็น True และ cell แรกของแถวแนวนอน และแนวตั้ง ไม่ว่าง(เพื่อดูว่าแถวทแยงมุมขวาชนะหรือเปล่า)ให้ return true ไปที่ function checkWinner
 
- ต่อไปเป็นการตรวจสอบแถว Anti Diagonal (เส้นทแยงมุมซ้าย)
  - ![image](https://github.com/user-attachments/assets/b40b40e9-16c9-41af-866d-5ed82136dd3f)
  - โดยจะขอแทน i เป็นตำแหน่งของ cell ใน board และใช้ค่า i - BoardSize ในการกำหนด index สำหรับตรวจสอบทแยงมุมซ้าย สมมุต i = 1,BoardSize = 3 [BoardSize - 1 - i] = 1 
  - ประกาศตัวแปร antiDiagWinner โดยกำหนด Default คือ True
  - วนลูป 1 ชั้น
  - โดยลูปชั้นแรกจะเป็นการวนลูปตำแหน่ง Column และ Row โดยจะเริ่มจากตำแหน่งที่ 2 ของ Column และ Row (index ของ array คือ 1) เพื่อที่จะสามารถตรวจสอบป้องกันการ indexOutBound และตรวจสอบว่าเซลปัจจุบันกับเซลก่อนหน้านี้มีค่าเหมือนกันไหม ถ้าไม่เหมือนกันให้กำหนด antiDiagWinner = False และ Break ออกจากลูป
  - กรณี antiDiagWinner ยังเป็น True และ cell แรกของแถวแนวนอน และแถวสุดท้ายของแนวตั้ง ไม่ว่าง(เพื่อดูว่าแถวทแยงมุมซ้ายชนะหรือเปล่า)ให้ return true ไปที่ function checkWinner
 
- ต่อไปเป็นการตรวจสอบเสมอ
  - ![image](https://github.com/user-attachments/assets/32bc74f3-df49-4a83-a9ac-d07b6468991d)
  - โดยการลูปตรวจสอบการเสอมจะเริ่มด้วยการลูปใน List ของ Board โดยเริ่มจาก x ในลูปจะทำการเก็บ Array ที่อยู่ใน List และ y จะทำการวนลูปใน x เพราะ x นั้นเก็บข้อมูล Array ที่อยู่ใน List นั้นเอง
  - โดยเงื่อนไขการตรวจสอบคือถ้า y นั้นมีค่าว่างหรือไม่ได้เก็บข้อมูลอะไรเลยใน Board (เจ้าพวกตัว X และ O) ให้ return false ไปที่ function checkDraw
  - แต่ถ้าไม่ตรงกับเงื่อนไขของข้อก่อนหน้านี้ก็จะทำการ Return true กลับไป



