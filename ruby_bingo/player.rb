require './bingo_card'

class Player

  # イニシャライザ
  def initialize(number)
    @bingo_card = BingoCard.new()
    @name = "Player#{number}さん"
  end

  # 自分のビンゴカードを表示するメソッド
  def print_card(count, previous_numbers)
    puts "\n#{@name}のカードです"
    @bingo_card.create_bingo(count,previous_numbers)
  end

  # ビンゴを判定するメソッド
  def bingo?(previous_numbers)
    bingo_numbers = @bingo_card.get_bingo_numbers
    card_width = BingoCard.get_card_width

    is_vertical_bingo = [*0..card_width].any? {|index| bingo_numbers.all? {|line| previous_numbers.include?(line[index])}}
    is_side_bingo = bingo_numbers.any? {|line| line.all? {|n| previous_numbers.include?(n)}}
    is_slant1_bingo = bingo_numbers.all? {|line| previous_numbers.include?(line[bingo_numbers.index(line)])}
    is_slant2_bingo = bingo_numbers.all? {|line| previous_numbers.include?(line[card_width - bingo_numbers.index(line) - 1])}

    is_vertical_bingo || is_side_bingo || is_slant1_bingo || is_slant2_bingo
  end

  # ========================================= getter

  def get_name
    @name
  end

end