class BingoCard

  attr_accessor :card_width, :numbers_max, :bingo_numbers

  # イニシャライザ
  def initialize(card_width)
    @card_width = card_width
    square_size = @card_width ** 2
    @numbers_max = @card_width <= 10 ? 100 : square_size
    @bingo_numbers = if @card_width <= 10
                       [*1..100].shuffle.slice(0, square_size).each_slice(@card_width).to_a
                     else
                       [*1..square_size].shuffle.slice(0, square_size).each_slice(@card_width).to_a
                     end
  end

  # 抽選状況に合わせたビンゴを表示するメソッド
  def print_bingo(previous_numbers)
    @bingo_numbers.each do |line|
      @card_width.times {print "+----"}
      puts "+"
      line.each do |n|
        print previous_numbers.include?(n) ? "|    " : "|#{sprintf("%3s", n)} "
      end
      puts "|"
    end
    @card_width.times {print "+----"}
    puts "+\n"
    unless previous_numbers.empty?
      puts @bingo_numbers.any? {|line| line.include?(previous_numbers.last)} ? "あたり！" : "残念！"
    end
  end

  # ビンゴを判定するメソッド
  def bingo?(previous_numbers)
    is_vertical_bingo = [*0..@card_width].any? {|index| bingo_numbers.all? {|line| previous_numbers.include?(line[index])}}
    is_side_bingo = bingo_numbers.any? {|line| line.all? {|n| previous_numbers.include?(n)}}
    is_slant1_bingo = bingo_numbers.all? {|line| previous_numbers.include?(line[bingo_numbers.index(line)])}
    is_slant2_bingo = bingo_numbers.all? {|line| previous_numbers.include?(line[@card_width - bingo_numbers.index(line) - 1])}

    is_vertical_bingo || is_side_bingo || is_slant1_bingo || is_slant2_bingo
  end

end