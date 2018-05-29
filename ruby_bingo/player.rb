require './bingo_card'

class Player
  attr_accessor :name, :bingo_card

  # イニシャライザ
  def initialize(number, card_width)
    @bingo_card = BingoCard.new(card_width)
    @name = "Player#{number}さん"
  end

  # 自分のビンゴカードを表示するメソッド
  def print_card(previous_numbers)
    puts "\n#{@name}のカードです"
    @bingo_card.create_bingo(previous_numbers)
  end
end
