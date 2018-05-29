require './player'

class Bingo

  # ビンゴのマス目の幅を入力するメソッド
  def select_width
    print "\nビンゴのマス目の幅を入力してください："
    card_width = gets.to_i
    BingoCard.card_width = card_width
  end

  # ゲームの参加人数を決めるメソッド
  def select_member
    print "\n何人でビンゴしますか？："
    @player_number = gets.to_i
  end

  # 抽選番号をだすメソッド
  def put_win_number
    win_number = rand(999) + 1
    if win_number <= BingoCard.numbers_max and !@previous_numbers.include?(win_number)
      @previous_numbers << win_number
    else
      put_win_number
    end
  end

  # 当選番号を表示するメソッド
  def print_win_number(count)
    puts "#{count}回目の抽選"
    puts "\n当選番号は#{@previous_numbers[count - 1]}です！"
  end

  # キーボード入力すると次の処理が始まるメソッド
  def key_input
    begin
      gets
    rescue
    end
  end

  # クラスを実行するメソッド
  def run
    puts "【BINGO GAME】"

    select_width
    select_member

    puts "Game Start!!"

    count = 0
    @previous_numbers = []

    @players = [*1..@player_number].map { |n| Player.new(n)}
    @players.each {|p| p.print_card(count,@previous_numbers)}

    loop do
      count += 1
      puts "\n↓抽選する↓"
      key_input
      put_win_number
      print_win_number(count)
      @players.each {|player| player.print_card(count,@previous_numbers)}
      @players.any?{|player| player.bingo?(@previous_numbers)} ? break : next
    end

    winner = @players.find {|p| p.bingo?(@previous_numbers)}
    puts "\n#{winner.name}のビンゴ！！！"
  end

end

Bingo.new.run