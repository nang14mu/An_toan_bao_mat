import RSA

p = 43
q = 47
e = 67

# a) Khóa công khai của An: PU = (e, n)
print("\na) Khóa công khai của An:")
N = p * q
publicKey = RSA.public_key(e, N)
print(f"   PU = (e, n) = ({publicKey.e}, {publicKey.N})")

# b) Cách An tạo ra khóa riêng: PR = (d, n)
print("\nb) Cách An tạo ra khóa riêng:")
phi = (p - 1) * (q - 1)
print(f"   Bước 1: Tính φ(n) = (p-1)(q-1) = {p-1} x {q-1} = {phi}")

d = RSA.find_modular_inverse(e, phi)
privateKey = RSA.private_key(d, p, q, N)
print(f"   Bước 2: Tìm d sao cho e x d ≡ 1 (mod φ(n))")
print(f"   Nghĩa là: {e} x d ≡ 1 (mod {phi})")
print(f"   => d = {d}")
print(f"   Kiểm tra: {e} x {d} mod {phi} = {(e * d) % phi}")
print(f"   PR = (d, n) = ({privateKey.d}, {privateKey.N})")

# c) Cách An tạo bản mã nhờa thông điệp M = 59 để gửi cho C
print("\nc) Cách An tạo bản mã nhờa (chữ ký) cho thông điệp M = 59:")
M = 59
print(f"   Thông điệp gốc: M = {M}")
print(f"   An sử dụng KHÓA RIÊNG của mình PR = ({d}, {N})")
print(f"   Công thức ký: S = M^d mod n")
S = RSA.sign(M, privateKey)
print(f"   S = {M}^{d} mod {N} = {S}")
print(f"   → An gửi cho C: (M = {M}, S = {S})")

# d) Hãy cho biết cách người nhận giải mã bản mã C
print("\nd) Cách người nhận C giải mã (xác thực chữ ký):")
print(f"   C nhận được: thông điệp M = {M} và chữ ký S = {S}")
print(f"   C sử dụng KHÓA CÔNG KHAI của An: PU = ({e}, {N})")
print(f"   Công thức xác thực: M' = S^e mod n")
M_verified = RSA.verify(S, publicKey)
print(f"   M' = {S}^{e} mod {N} = {M_verified}")
print(f"   So sánh: M = {M}, M' = {M_verified}")
if M == M_verified:
    print(f"   ✓ M = M' → Chữ ký HỢP LỆ, xác nhận được gửi từ An!")
else:
    print(f"   ✗ M ≠ M' → Chữ ký KHÔNG HỢP LỆ!")

# e) Việc mã hóa ở câu c) thực hiện nhiệm vụ gì? Ký số hay bảo mật?
print("\ne) Việc mã hóa ở câu c) thực hiện nhiệm vụ gì?")
print("   Trả lời: KÝ SỐ (Digital Signature)")
print("   Giải thích:")
print("   - An sử dụng KHÓA RIÊNG của mình để 'mã hóa' (ký)")
print("   - Mọi người có khóa công khai của An đều có thể 'giải mã' (xác thực)")
print("   - Mục đích: XÁC THỰC nguồn gốc và TOÀN VẸN của thông điệp")
print("   - Chứng minh rằng thông điệp THỰC SỰ từ An (chỉ An có khóa riêng)")
print("   - Thông điệp KHÔNG BÍ MẬT (ai cũng xác thực được)")
