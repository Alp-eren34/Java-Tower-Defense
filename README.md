# Java Tower Defense Game

A comprehensive Tower Defense game implemented in Java with interactive gameplay, multiple enemy types, tower management, and wave-based level system.

## 📋 Project Overview

This is a Swing-based Tower Defense game (Prolab Project Assignment) featuring:
- Strategic tower placement on a customizable grid
- Multiple tower types with different projectile mechanics
- Various enemy types (Orc, Bat, Knight) with different behaviors
- Wave-based enemy spawning system
- Level editing mode for custom map creation
- Gold/resource management system
- Health and damage mechanics

## 🎮 Features

### Core Gameplay
- **Tower Defense Mechanics**: Place towers to defend against waves of enemies
- **Multiple Tower Types**: Different towers with unique projectile types (Arrow, Bomb, Chain)
- **Enemy Variety**: 
  - Orc (10 gold reward)
  - Bat (15 gold reward)
  - Knight (20 gold reward)
- **Wave System**: Progressive waves of enemies with increasing difficulty
- **Resource Management**: Earn gold by defeating enemies, spend it on towers

### Game States
- **Menu**: Main game menu
- **Playing**: Active gameplay
- **Editing**: Level editor to design custom maps
- **Game Over**: Defeat screen
- **You Won**: Victory screen

### Technical Features
- 120 FPS rendering
- 60 UPS (Updates Per Second) game logic
- Save/Load system for levels and game progress
- Logging system for debugging and tracking game events
- Mouse and keyboard input handling
- Sprite-based graphics with sprite atlases

## 🏗️ Project Structure

```
src/
├── main/                 # Core game engine
│   ├── Game.java        # Main game class and entry point
│   ├── GameScreen.java  # Game rendering surface
│   ├── GameStates.java  # Game state management
│   └── Render.java      # Rendering system
├── scenes/              # Game scenes/screens
│   ├── Menu.java        # Main menu
│   ├── Playing.java     # Active gameplay scene
│   ├── Editing.java     # Level editor scene
│   ├── GameOver.java    # Defeat screen
│   ├── YouWon.java      # Victory screen
│   └── SceneMethods.java# Common scene methods
├── ui/                  # User interface components
│   ├── ActionBar.java   # Action/tower selection bar
│   ├── MyButton.java    # Custom button component
│   ├── Toolbar.java     # Toolbar UI
│   └── Bar.java         # Generic bar UI element
├── objects/             # Game objects (towers, projectiles, etc.)
├── enemies/             # Enemy classes
│   ├── Enemy.java       # Base enemy class
│   ├── Orc.java         # Orc enemy
│   ├── Bat.java         # Bat enemy
│   └── Knight.java      # Knight enemy
├── events/              # Event system
│   └── Wave.java        # Wave/spawning system
├── managers/            # Manager classes
│   └── TileManager.java # Grid/tile management
├── inputs/              # Input handling
│   ├── KeyBoardListener.java   # Keyboard input
│   └── MyMouseListeners.java   # Mouse input
├── helps/               # Utility classes
│   ├── Constants.java   # Game constants and configuration
│   ├── LoadSave.java    # File I/O for save/load operations
│   └── ImgFix.java      # Image processing utilities
└── res/                 # Game resources
    ├── spriteatlas.png  # Sprite sheet for game objects
    ├── spriteatlas2.png # Additional sprite sheet
    ├── mainScreen.jpg   # Main menu background
    ├── gameOver.jpg     # Game over screen image
    ├── victory.jpg      # Victory screen image
    └── *.txt            # Level and configuration files
```

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK 8 or higher)
- IDE (IntelliJ IDEA, Eclipse, NetBeans, or VS Code with Java extension)

### Installation

1. **Clone the repository:**
```bash
git clone https://github.com/Alp-eren34/Java-Tower-Defense.git
cd Java-Tower-Defense
```

2. **Import into your IDE:**
   - Open the project directory
   - The project should automatically recognize it as a Java project
   - Configure the JDK version in your IDE settings

3. **Run the game:**
```bash
javac -d . src/**/*.java
java main.Game
```

Or simply run the `Game.java` file from your IDE.

## 🎯 How to Play

### Main Menu
- Start a new game
- Access the level editor
- Navigate through game options

### Gameplay
- **Place Towers**: Click on the toolbar to select a tower type, then click on the grid to place it
- **Manage Resources**: Defeat enemies to earn gold, use gold to purchase towers
- **Survive Waves**: Defend your base from progressively harder waves of enemies
- **Win Condition**: Successfully defend against all waves

### Level Editor (EDIT mode)
- Create custom maps by placing tiles and paths
- Design enemy routes
- Save your custom levels

## ⌨️ Controls

- **Mouse**: Click to interact with buttons, place towers, etc.
- **Keyboard**: Navigate menus and alternate between game modes
- **ESC**: Return to main menu or exit current screen

## 🛠️ Development

### Key Classes

- **`Game.java`**: Main application entry point, manages game lifecycle and state transitions
- **`Playing.java`**: Core gameplay logic, manages towers, enemies, and game progression
- **`Editing.java`**: Level editor functionality
- **`Enemy.java`**: Base class for all enemy types with common behaviors
- **`Constants.java`**: Configuration constants for game balance and mechanics

### File Format
- Level files stored in `res/` folder as `.txt` files
- Game logs automatically created in `savunma_gunlugu.txt`

### Configuration
Edit `Constants.java` to modify:
- Enemy rewards and damage
- Projectile speeds
- Tower costs
- Other game balance parameters

## 📊 Game Balance

### Enemy Rewards
- Orc: 10 gold
- Bat: 15 gold
- Knight: 20 gold

### Projectile Types
- **Arrow**: Speed 8.0, standard projectile
- **Bomb**: Speed 4.0, explosive splash damage
- **Chain**: Speed 6.0, chaining projectile

### Game Performance
- **Rendering**: 120 FPS
- **Game Logic**: 60 UPS
- **Resolution**: Configurable tile-based grid system

## 📝 Logging

The game automatically creates a log file (`savunma_gunlugu.txt`) that tracks:
- Game start/end events
- Gameplay milestones
- Debug information

## 🐛 Troubleshooting

### Game won't start
- Ensure JDK is properly installed and configured
- Check that all `.java` files are included in the compilation
- Verify resource files in `res/` folder exist

### Graphics issues
- Ensure sprite atlas files (`spriteatlas.png`, `spriteatlas2.png`) exist in `res/`
- Check image paths in `ImgFix.java`

### Performance issues
- Check system resources
- Reduce tile count in level editor
- Verify FPS/UPS settings in `Game.java`

## 🤝 Contributing

To contribute improvements:
1. Fork the repository
2. Create a feature branch
3. Make your improvements
4. Submit a pull request

## 📄 License

This project is an educational assignment (Prolab Project). Please check the repository for license information.

## 👤 Author

**Alp Eren**
- GitHub: [@Alp-eren34](https://github.com/Alp-eren34)

## 📚 Resources

### Java Swing Documentation
- [Java Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- [JFrame API](https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html)

### Game Development
- Tower Defense game mechanics patterns
- Java game loop implementation
- Sprite-based rendering techniques

---

**Last Updated**: March 2026

For questions or issues, please check the GitHub repository or create an issue.

---

# 🇹🇷 Java Tower Defense Oyunu - Türkçe Dokümantasyon

Etkileşimli oynanış, çok sayıda düşman türü, kule yönetimi ve dalga tabanlı seviye sistemi içeren Java'da uygulanmış kapsamlı bir Tower Defense oyunu.

## 📋 Proje Özeti

Bu, Swing tabanlı bir Tower Defense oyunudur (Prolab Proje Ödevi) ve şunları içerir:
- Özelleştirilebilir bir ızgara üzerinde stratejik kule yerleşimi
- Farklı mermi mekaniğine sahip çok sayıda kule türü
- Farklı davranışlara sahip çeşitli düşman türleri (Orc, Bat, Knight)
- Dalga tabanlı düşman üretim sistemi
- Özel harita oluşturma için seviye düzenleme modu
- Altın/kaynak yönetim sistemi
- Sağlık ve hasar mekanikları

## 🎮 Özellikler

### Temel Oynanış
- **Tower Defense Mekanikler**: Düşman dalgalarına karşı savunmak için kuleler kurun
- **Çok Sayıda Kule Türü**: Farklı mermi türlerine sahip farklı kuleler (Ok, Bomba, Zincir)
- **Düşman Çeşitliliği**: 
  - Orc (10 altın ödülü)
  - Bat (15 altın ödülü)
  - Knight (20 altın ödülü)
- **Dalga Sistemi**: Zorluk derecesi artan ilerleme aşamaları
- **Kaynak Yönetimi**: Düşmanları yenerek altın kazanın, kuleler için harcayın

### Oyun Durumları
- **Menü**: Ana oyun menüsü
- **Oynanış**: Aktif oynanış
- **Düzenleme**: Özel haritalar tasarlamak için seviye editörü
- **Oyun Bitti**: Yenilgi ekranı
- **Kazandınız**: Zafer ekranı

### Teknik Özellikler
- 120 FPS işleme hızı
- 60 UPS (Saniye Başına Güncellemeler) oyun mantığı
- Seviyeleri ve oyun ilerlemesini kaydetme/yükleme sistemi
- Hata ayıklama ve oyun olaylarını izlemek için günlük sistemi
- Fare ve klavye giriş işleme
- Sprite tabanlı grafikler ve sprite atlasları

## 🏗️ Proje Yapısı

```
src/
├── main/                 # Ana oyun motoru
│   ├── Game.java        # Ana oyun sınıfı ve başlangıç noktası
│   ├── GameScreen.java  # Oyun rendere yüzeyi
│   ├── GameStates.java  # Oyun durumu yönetimi
│   └── Render.java      # Rendereleme sistemi
├── scenes/              # Oyun sahneleri/ekranları
│   ├── Menu.java        # Ana menü
│   ├── Playing.java     # Aktif oynanış sahnesi
│   ├── Editing.java     # Seviye editörü sahnesi
│   ├── GameOver.java    # Yenilgi ekranı
│   ├── YouWon.java      # Zafer ekranı
│   └── SceneMethods.java# Ortak sahne yöntemleri
├── ui/                  # Kullanıcı arayüzü bileşenleri
│   ├── ActionBar.java   # Aksiyon/kule seçim çubuğu
│   ├── MyButton.java    # Özel düğme bileşeni
│   ├── Toolbar.java     # Araç çubuğu arayüzü
│   └── Bar.java         # Genel çubuk arayüzü öğesi
├── objects/             # Oyun nesneleri (kuleler, mermiler, vb.)
├── enemies/             # Düşman sınıfları
│   ├── Enemy.java       # Temel düşman sınıfı
│   ├── Orc.java         # Orc düşmanı
│   ├── Bat.java         # Bat düşmanı
│   └── Knight.java      # Knight düşmanı
├── events/              # Olay sistemi
│   └── Wave.java        # Dalga/üretim sistemi
├── managers/            # Yönetici sınıfları
│   └── TileManager.java # Izgara/fayans yönetimi
├── inputs/              # Giriş işleme
│   ├── KeyBoardListener.java   # Klavye girdisi
│   └── MyMouseListeners.java   # Fare girdisi
├── helps/               # Yardımcı sınıflar
│   ├── Constants.java   # Oyun sabitleri ve konfigürasyonu
│   ├── LoadSave.java    # Kaydetme/yükleme dosya I/O işlemleri
│   └── ImgFix.java      # Görüntü işleme yardımcıları
└── res/                 # Oyun kaynakları
    ├── spriteatlas.png  # Oyun nesneleri için sprite sayfası
    ├── spriteatlas2.png # Ek sprite sayfası
    ├── mainScreen.jpg   # Ana menü arka planı
    ├── gameOver.jpg     # Oyun bitti ekranı görüntüsü
    ├── victory.jpg      # Zafer ekranı görüntüsü
    └── *.txt            # Seviye ve konfigürasyon dosyaları
```

## 🚀 Başlangıç

### Ön Koşullar
- Java Development Kit (JDK 8 veya üstü)
- IDE (IntelliJ IDEA, Eclipse, NetBeans veya Java uzantılı VS Code)

### Kurulum

1. **Repository'i klonlayın:**
```bash
git clone https://github.com/Alp-eren34/Java-Tower-Defense.git
cd Java-Tower-Defense
```

2. **IDE'nize aktarın:**
   - Proje dizinini açın
   - Proje otomatik olarak Java projesi olarak tanınmalı
   - IDE ayarlarında JDK sürümünü yapılandırın

3. **Oyunu çalıştırın:**
```bash
javac -d . src/**/*.java
java main.Game
```

Ya da IDE'den `Game.java` dosyasını çalıştırabilirsiniz.

## 🎯 Nasıl Oynanır?

### Ana Menü
- Yeni oyun başlatın
- Seviye editörüne erişin
- Oyun seçeneklerinde gezinin

### Oynanış
- **Kule Yerleştir**: Araç çubuğundan bir kule türü seçin, ardından ızgaraya tıklayarak yerleştirin
- **Kaynakları Yönet**: Düşmanları yenerek altın kazanın, kuleler satın almak için altın harcayın
- **Dalgalara Karşı Direnin**: Giderek zorlaşan düşman dalgalarından tabanızı savunun
- **Kazanma Koşulu**: Tüm dalgalara karşı başarıyla savunun

### Seviye Editörü (EDIT modu)
- Fayanslar ve yollar yerleştirerek özel haritalar oluşturun
- Düşman rotaları tasarlayın
- Özel seviyeleri kaydedin

## ⌨️ Kontroller

- **Fare**: Düğmelere tıklamak, kuleler yerleştirmek, vb. için kullanın
- **Klavye**: Menülerde gezinin ve oyun modları arasında geçiş yapın
- **ESC**: Ana menüye dönün veya mevcut ekrandan çıkın

## 🛠️ Geliştirme

### Temel Sınıflar

- **`Game.java`**: Ana uygulama başlangıç noktası, oyun yaşam döngüsü ve durum geçişlerini yönetir
- **`Playing.java`**: Temel oynanış mantığı, kuleler, düşmanlar ve oyun ilerlemesini yönetir
- **`Editing.java`**: Seviye editörü işlevselliği
- **`Enemy.java`**: Tüm düşman türleri için temel sınıf ortak davranışlar
- **`Constants.java`**: Oyun dengesi ve mekanikler için konfigürasyon sabitleri

### Dosya Biçimi
- Seviye dosyaları `res/` klasöründe `.txt` dosyaları olarak depolanır
- Oyun günlükleri otomatik olarak `savunma_gunlugu.txt` dosyasında oluşturulur

### Konfigürasyon
Oyun dengesini değiştirmek için `Constants.java` dosyasını düzenleyin:
- Düşman ödülleri ve hasarı
- Mermi hızları
- Kule maliyetleri
- Diğer oyun denge parametreleri

## 📊 Oyun Dengesi

### Düşman Ödülleri
- Orc: 10 altın
- Bat: 15 altın
- Knight: 20 altın

### Mermi Türleri
- **Ok**: Hız 8.0, standart mermi
- **Bomba**: Hız 4.0, patlayıcı sıçrama hasarı
- **Zincir**: Hız 6.0, zincir mermisi

### Oyun Performansı
- **Rendereleme**: 120 FPS
- **Oyun Mantığı**: 60 UPS
- **Çözünürlük**: Yapılandırılabilir fayans tabanlı ızgara sistemi

## 📝 Günlük Sistemi

Oyun otomatik olarak şunları izleyen bir günlük dosyası (`savunma_gunlugu.txt`) oluşturur:
- Oyun başlangıç/bitiş olayları
- Oynanış ana noktaları
- Hata ayıklama bilgileri

## 🐛 Sorun Giderme

### Oyun başlamıyor
- JDK'nın düzgün şekilde yüklendiğinden ve yapılandırıldığından emin olun
- Tüm `.java` dosyalarının derlemeye dahil edildiğini kontrol edin
- `res/` klasöründeki kaynak dosyalarının var olduğunu doğrulayın

### Grafik sorunları
- Sprite atlas dosyalarının (`spriteatlas.png`, `spriteatlas2.png`) `res/` klasöründe var olduğundan emin olun
- `ImgFix.java` dosyasında görüntü yollarını kontrol edin

### Performans sorunları
- Sistem kaynaklarını kontrol edin
- Seviye editöründe fayans sayısını azaltın
- `Game.java` dosyasında FPS/UPS ayarlarını doğrulayın

## 🤝 Katkıda Bulunma

İyileştirmeler yapmak için:
1. Repository'i fork edin
2. Bir özellik dalı oluşturun
3. İyileştirmelerinizi yapın
4. Bir pull request gönderin

## 📄 Lisans

Bu proje eğitim amaçlı bir ödevi (Prolab Projesi). Lisans bilgisi için lütfen repository'i kontrol edin.

## 👤 Yazar

**Alp Eren**
- GitHub: [@Alp-eren34](https://github.com/Alp-eren34)

## 📚 Kaynaklar

### Java Swing Dokümantasyonu
- [Java Swing Öğreticisi](https://docs.oracle.com/javase/tutorial/uiswing/)
- [JFrame API](https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html)

### Oyun Geliştirme
- Tower Defense oyun mekanikler desenleri
- Java oyun döngüsü uygulaması
- Sprite tabanlı rendereleme teknikleri

---

**Son Güncelleme**: Mart 2026

Sorular veya sorunlar için lütfen GitHub repository'sini kontrol edin veya bir issue oluşturun.
