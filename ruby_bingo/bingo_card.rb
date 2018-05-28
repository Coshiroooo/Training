class BingoCard

  @@card_width
  @@numbers_max

  attr_accessor :bingo_numbers

  # イニシャライザ
  def initialize
    @@numbers_max = @@card_width <= 10 ? 100 : @@card_width ** 2
    @bingo_numbers = if @@card_width <= 10
                       [*1..100].shuffle!.slice(0, @@card_width ** 2).each_slice(@@card_width).to_a
                     else
                       [*1..@@card_width ** 2].shuffle!.slice(0, @@card_width ** 2).each_slice(@@card_width).to_a
                     end
  end

  # 抽選状況に合わせたビンゴを表示するメソッド
  def create_bingo(count, previous_numbers)
    @bingo_numbers.each do |line|
      @@card_width.times {print "+----"}
      puts "+"
      line.each do |n|
        print previous_numbers.include?(n) ? "|    " : "|#{sprintf("%3s", n)} "
      end
      puts "|"
    end
    @@card_width.times {print "+----"}
    puts "+\n"
    if count > 0
      puts @bingo_numbers.any? {|line| line.include?(previous_numbers[count - 1])} ? "あたり！" : "残念！"
    end
  end

  def self.card_width=(value)
    @@card_width = value
  end

  def self.card_width
    @@card_width
  end

  def self.numbers_max
    @@numbers_max
  end

end