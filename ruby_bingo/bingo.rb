require './player'

class Bingo
  # ビンゴのマス目の幅を入力するメソッド
  def select_width
    print "\nビンゴのマス目の幅を入力してください："
    card_width = gets.to_i
    card_width
  end

  # ゲームの参加人数を決めるメソッド
  def select_member
    print "\n何人でビンゴしますか？："
    @player_number = gets.to_i
  end

  # 抽選番号をだすメソッド
  def put_win_number(numbers_max)
    win_number = rand(1..999)
    if (win_number <= numbers_max) && !@previous_numbers.include?(win_number)
      @previous_numbers << win_number
    else
      put_win_number(numbers_max)
    end
  end

  # 当選番号を表示するメソッド
  def print_win_number(count)
    puts "#{count}回目の抽選"
    puts "\n当選番号は#{@previous_numbers.last}です！"
  end

  # キーボード入力すると次の処理が始まるメソッド
  def key_input
    gets
  rescue StandardError
  end

  # クラスを実行するメソッド
  def run
    puts '【BINGO GAME】'

    card_width = select_width
    numbers_max = BingoCard.new(card_width).numbers_max

    select_member

    puts 'Game Start!!'

    @previous_numbers = []

    @players = [*1..@player_number].map { |n| Player.new(n, card_width) }
    @players.each { |p| p.print_my_card(@previous_numbers) }

    1.step do |count|
      puts "\n↓抽選する↓"
      key_input
      put_win_number(numbers_max)
      print_win_number(count)
      @players.each { |player| player.print_my_card(@previous_numbers) }
      @players.any? { |player| player.bingo_card.bingo?(@previous_numbers) } ? break : next
    end

    winner = @players.find { |p| p.bingo_card.bingo?(@previous_numbers) }
    puts "\n#{winner.name}のビンゴ！！！"
  end
end

Bingo.new.run
