import RSA

p = 43
q = 47
e = 67
    
    
print("\na) Khóa công khai của An:")
N = p * q
publicKey = RSA.public_key(e, N)
print(f"   PU = (e, n) = ({publicKey.e}, {publicKey.N})")
    

print("\nb) Cách An tạo ra khóa riêng:")
phi = (p - 1) * (q - 1)
print(f"   Bước 1: Tính φ(n) = (p-1)(q-1) = {p-1} × {q-1} = {phi}")
    
d = RSA.find_modular_inverse(e, phi)
privateKey = RSA.private_key(d, p, q, N)
print(f"   Bước 2: Tìm d sao cho e x d ≡ 1 (mod φ(n))")
print(f"   Nghĩa là: {e} x d ≡ 1 (mod {phi})")
print(f"   => d = {d}")
print(f"   Kiểm tra: {e} x {d} mod {phi} = {(e * d) % phi}")
print(f"   PR = (d, n) = ({privateKey.d}, {privateKey.N})")
    
print("\nc) Cách người gửi (Bình) mã hóa thông điệp M = 59:")
M = 59
print(f"   Thông điệp gốc: M = {M}")
C = RSA.encrypt(M, publicKey)
print(f"   Sử dụng khóa công khai PU = ({e}, {N})")
print(f"   Công thức mã hóa: C = M^e mod n")
print(f"   C = {M}^{e} mod {N} = {C}")
    
print("\nd) Cách An giải mã bản mã C:")
print(f"   Bản mã nhận được: C = {C}")
print(f"   Sử dụng khóa riêng PR = ({d}, {N})")
print(f"   Công thức giải mã: M = C^d mod n")
M_decrypted = RSA.decrypt(C, privateKey)
print(f"   M = {C}^{d} mod {N} = {M_decrypted}")
print(f"   ✓ Thông điệp gốc được khôi phục: M = {M_decrypted}")
    
print("\ne) Việc mã hóa ở câu c) có nhiệm vụ gì?")
print("   Trả lời: BẢO MẬT")
print("   Giải thích:")
print("   - Bình sử dụng KHÓA CÔNG KHAI của An để mã hóa")
print("   - Chỉ An (người có khóa riêng) mới giải mã được")
print("   - Đảm bảo tính BÍ MẬT của thông điệp")
print("   - Nếu là KÝ SỐ: người gửi sẽ dùng khóa riêng của mình để mã hóa,")
print("     và mọi người có khóa công khai đều có thể giải mã để xác thực")
